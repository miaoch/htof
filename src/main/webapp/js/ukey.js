/**
 * 创建目录 pName: 创建的项目名称
 */
function CreateDir(pName) {
	try {
		ePass.OpenDevice(1, "");
		// 验证Pin码
		try {
			var pin = document.all.qdPINID.value;
			ePass.VerifyPIN(0, pin);
			// alert("验证Pin成功");
		} catch (e) {
			alert("验证pin失败");
			return;
		}

	} catch (e) {
		alert("请检查key是否插入！");
	}
	var lFlags = 0x00010000;
	var pGUID = 0;
	var dirInfoID = 100;

	try {
		ePass.CreateDir(lFlags, pName, pGUID, dirInfoID);
	} catch (e) {
		// 0x00000300根据文件名称进行删除
		ePass.DeleteDir(0x00000300, pGUID, pName);
		ePass.CreateDir(lFlags, pName, pGUID, dirInfoID);
	}
	ePass.CloseDevice();
}

/**
 * 创建文件 fileID 文件编号，使用十六进制，例如：0x01
 */
function CreateFile(pName, epsFileID, pBuffer) {
	ePass.OpenDevice(1, "");
	ePass.ChangeDir(0x0300, 0, pName);// 改变目录
	// 文件长度
	var epsFileSize = 16;
	/*
	 * EPAS_FILETYPE_UNUSED 0x00 EPAS_FILETYPE_DIR 0x01 EPAS_FILETYPE_DATA 0x02
	 * EPAS_FILETYPE_MD5 0x04 EPAS_FILETYPE_TEA 0x08 EPAS_FILETYPE_UNKNOWN 0xFF
	 * 权限： EPAS_ACCESS_ANYONE 0x00 EPAS_ACCESS_USER 0x01 EPAS_ACCESS_OFFICER
	 * 0x02 EPAS_ACCESS_NONE 0x07
	 * 
	 */
	var epsFileType = 0x04;
	// 读去权限
	var epsFileReadAccess = 1;
	// 写入权限
	var epsFileWriteAccess = 1;
	// 加密权限
	var epsFileCryptAccess = 1;
	// 创建文件
	ePass.CreateFile(0, epsFileID, epsFileSize, epsFileType, epsFileReadAccess,
			epsFileWriteAccess, epsFileCryptAccess, 2);

	/*
	 * 0 打开文件使用当前授权访问 0x00000010 EPAS_FILE_READ Open the file for reading.
	 * 0x00000020 EPAS_FILE_WRITE Open the file for writing. 0x00000040
	 * EPAS_FILE_CRYPT Open the file for cryptographic operations.
	 */
	ePass.OpenFile(2, epsFileID);

	// 0 ASCII数据 1 二进制数据
	var lType = 1;
	// 保留用于未来的扩展，并且必须设置为零。
	var lFlags = 0;
	// 从哪个字节开始
	var lOffset = 0;
	// 待写入的字节数
	var lBytesToWrite = 16;

	ePass.write(lType, lFlags, lOffset, pBuffer, lBytesToWrite);
	ePass.CloseDevice();
}

/**
 * 初始化key 1 创建目录 2 创建文件 3 写入秘钥
 */
function iniKey() {

	/** ********创建目录******* */
	var pName = document.all.cspNameID.value;
	CreateDir(pName);
	/** ********创建文件和数据******* */
	var key = document.all.cspKeyID.value;
	// 第一个文件
	var epsFileID = 0x01;
	var kIpad = MD5HMAC(0, key);
	// alert("kIpad:==="+kIpad);
	CreateFile(pName, epsFileID, kIpad);
	// 第二个文件
	epsFileID = 0x02;
	var kOpad = MD5HMAC(1, key);
	// alert("kOpad:==="+kOpad);
	CreateFile(pName, epsFileID, kOpad);
	alert("初始化成功！");

}

function MD5HMAC(lFlags, pAuthKey) {
	ePass.OpenDevice(1, "");
	/*
	 * SOFT_MD5HMAC_IPAD_KEY 0x00 return an array of 16 bytes that receives MD5
	 * (SECRET XOR iPad). SOFT_MD5HMAC_OPAD_KEY 0x01 return an array of 16 bytes
	 * that receives MD5(SECRET XOR oPad). SOFT_MD5HMAC_DIGEST 0x02 return 16
	 * bits digests of the input message.
	 */
	var pRetVal = ePass.Soft_MD5HMAC_Ex(lFlags, "mytest", pAuthKey);
	ePass.CloseDevice();

	return pRetVal;
}

function changeHexToASCII(hexStr) {
	var tmp = "";
	var aiisStr = "";
	var pint = "";
	for (var i = 0; i < hexStr.length; i++) {
		var c1 = hexStr.charAt(i);
		var c2 = hexStr.charAt(i + 1);
		tmp += c1;
		tmp += c2;
		i++;
		// 计算aiis码
		var y = parseInt(tmp, 16);
		pint += y + ","
		var cp = String.fromCharCode(y);
		aiisStr += cp;
		tmp = "";
	}
	// alert("pint:"+pint);
	return aiisStr;
}

/**
 * 获取计算结果 pName 初始化的时候的项目名称 userPIN 用户PIN码 data 待计算的原文
 * 
 */
function getHashToken(pName, userPIN, data) {
	var obj = ePass;
	// 读取版本号
	// alert(ePass.GetLibVersion());

	// 打开设备
	ePass.OpenDevice(1, "");

	// 读取设备序列号
	// var snID = ePass.GetStrProperty(7,0,0);
	// alert("序列号为："+snID);

	// 验证Pin码
	try {
		ePass.VerifyPIN(0, userPIN);
		// alert("验证Pin成功");
	} catch (e) {
		alert("验证pin失败");
		return;
	}

	// 生成随机数
	var rand = ePass.GenRandom(0);
	// alert("随机数为："+rand);

	/**
	 * 
	 * 计算MD5_HMAC 前提需要key里面需要个人化信息
	 */
	try {
		/*
		 * 根据名称获取：0x0300 0x300 768
		 */
		ePass.ChangeDir(0x0300, 0, pName);// 改变目录
	} catch (e) {
		alert(e.message);
		return;
	}

	ePass.OpenFile(0, 1);// 打开key里的文件夹

	// 1 代表初始化MD5
	var lFlags = 1;
	// 第二个文件的ID,第一个文件为当前打开的文件
	var lRefData = 2;
	var s = ePass.HashToken(1, 2, data);
	// alert(s);
	// 关闭设备
	ePass.CloseDevice();

	return s;
}

/**
 * 
 * 前端计算结果
 */
function getMD5HMAC() {
	var pName = document.all.qdpNameID.value;
	var userPIN = document.all.qdPINID.value;
	var data = document.all.qdData.value;
	var s = getHashToken(pName, userPIN, data);
	document.all.qdJSID.value = s;

}

/**
 * 
 * 从后台计算计算
 */
function getHDJG() {
	var key = document.all.hdKeyID.value;
	var data = document.all.hdDataID.value;

	var s = "key=" + encodeURIComponent(key) + "&data="
			+ encodeURIComponent(data);
	// 调用后台
	$.ajax({
		type : "POST",
		url : "/ftd/public/epass.do?id=1000",
		dataType : "text",
		data : s,
		async : true, // true异步执行
		success : function(result) {
			document.all.hdJSID.value = result;
		},
		error : function(response, textStatus, errorThrown) {
			alert("响应失败：" + response.status);
		}
	});
}

/**
 * 控制LED灯是不是亮 bool true的时候亮起，其他关闭
 * 
 */
function controlLED(bool) {
	if (bool) {
		ePass.OpenDevice(1, "");
		ePass.SetProperty(0x08, "", "", 0);
		ePass.CloseDevice();
	} else {
		ePass.OpenDevice(1, "");
		ePass.SetProperty(0x09, "", "", 0);
		ePass.CloseDevice();
	}
}

function setOnload() {
	// 读取设备序列号
	var obj = ePass;
	try {
		ePass.OpenDevice(1, "");
		var snID = ePass.GetStrProperty(7, 0, 0);
		//document.all.xlhID.value = snID;
		$("#keyStats").html("连接正常");
		$("#keyStats").attr("style", "color:green;");
		ePass.CloseDevice();
	} catch (e) {
		$("#keyStats").html("连接异常");
		$("#keyStats").attr("style", "color:red;");
	}
}

/**
 * 实现重置密码
 */
function resetPass() {
	var adminPin = "rockey";
	var new_pass = "1234";
	try {
		ePass.OpenDevice(1, "");
		ePass.ChangeCode(1, adminPin, new_pass);
		alert("重置成功，重置后密码为:1234");
	} catch (e) {
		alert("修改失败,请确认管理员密码是否做了修改！");
	}
	ePass.CloseDevice();
}

/**
 * 修改密码
 */
function changePass() {
	var old_pass = $("#old_pass_id").val();
	var new_pass = $("#new_pass_id").val();
	try {
		ePass.OpenDevice(1, "");
		ePass.ChangeCode(0, old_pass, new_pass);
		alert("修改成功");
	} catch (e) {
		alert("修改失败,请确认原密码是否正确");
	}
	ePass.CloseDevice();
}
