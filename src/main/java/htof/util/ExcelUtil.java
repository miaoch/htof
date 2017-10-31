package htof.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtil {
	private static final int DEFAUL_SHEET = 0;
	private Workbook wb = null;  
	private Sheet sheet = null;
	private String filePath;
	private int currentsheet = -1;
	
	public ExcelUtil (String filePath) throws IOException {
		this.filePath = filePath;
		init(filePath);
	}
	
	/**
	 * �����ļ�·��������Workbook Sheet Row����
	 * @param filePath
	 * @throws IOException 
	 */
    private void init(String filePath) throws IOException  {  
		InputStream fileItem = new FileInputStream(new File(filePath));
		String suffix = filePath.substring(filePath.lastIndexOf("."));
		if (".xls".equals(suffix)) {  
			wb = new HSSFWorkbook(fileItem);  
		} else if(".xlsx".equals(suffix)) {  
			wb = new XSSFWorkbook(fileItem);  
		} else {  
			wb = null;
		}  
		if (wb != null) {
			if (currentsheet != -1) {
				sheet = wb.getSheetAt(currentsheet);
			} else {
				sheet = wb.getSheetAt(DEFAUL_SHEET);
				currentsheet = DEFAUL_SHEET;
			}
		}
    }
    
    public void setSheet(int sheetNO) {
    	this.sheet = wb.getSheetAt(sheetNO);
    	if (sheet != null) {
    		currentsheet = getSheetIndex();
    	}
    }
    
    public void setSheet(String sheetName) {
    	this.sheet = wb.getSheet(sheetName);
    	if (sheet != null) {
    		currentsheet = getSheetIndex();
    	}
    }
    
    public int getSheetIndex() {
    	return wb.getSheetIndex(this.sheet);
    }
    
    public String getSheetName() {
    	return getSheetIndex() != -1?
    			wb.getSheetName(getSheetIndex()):"unknow";
    }
    
    /**
     * �����У���ȡ���ݡ�
     * @param index
     * @return String[]
     */
    public String[] readRowData(int index) {
    	String[] result = null;
    	if (sheet != null && index <= sheet.getLastRowNum()) {
    		Row row = sheet.getRow(index);
    		if (row != null) {
    			int colnum = row.getLastCellNum();
        		result = new String[colnum];
        		for (int i=0; i<colnum; i++) {
        			Cell cell = row.getCell(i);
        			if (cell != null) {
        				switch (cell.getCellType()) {
        				case HSSFCell.CELL_TYPE_BLANK:
        				case HSSFCell.CELL_TYPE_ERROR:
        					result[i] = "null";
        					break;
        				case HSSFCell.CELL_TYPE_FORMULA:
        				case HSSFCell.CELL_TYPE_NUMERIC:
        					//result[i] = String.valueOf(new BigDecimal(String.valueOf(cell.getNumericCellValue())).stripTrailingZeros());
        					String num = String.valueOf(cell.getNumericCellValue());
        					result[i] = num.substring(0, num.indexOf("."));
        					break;
        				case HSSFCell.CELL_TYPE_BOOLEAN:
        					result[i] = String.valueOf(cell.getBooleanCellValue());
        					break;
        				case HSSFCell.CELL_TYPE_STRING:
        					result[i] = cell.getStringCellValue();
        					break;
        				default:
        					result[i] = "null";
        				}
        				
        			} else {
        				result[i] = "null";
        			}
        		}
    		}
    	}
    	return result;
    }
    
    /**
     * ��ȡ�������ݡ�
     * @return String[][]
     */
    public String[][] readRowData() {
    	String[][] result = null;
    	int rownum = sheet.getLastRowNum();
    	result = new String[rownum+1][];
    	for (int i=0; i<=rownum; i++) {
    		String[] row = readRowData(i);
    		result[i] = row;
    	}
    	return result;
    }
    
    /**
     * ��wbд�����
     */
    public void write() {
    	OutputStream stream = null;
		try {
			stream = new FileOutputStream(filePath);
			wb.write(stream);
			init(filePath);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				stream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
    }
    
    /**
     * ����һ��ֵ���飬��ӵ���ǰ���ӱ������档
     * @param rowdata
     */
    public void writeRowData(Object[] rowdata) {
    	writeRowData(sheet.getLastRowNum() + 1, rowdata);
    }
    
    /**
     * ����һ��������ӵ���ǰ���ӱ������档
     * @param data
     */
    public void writeRowData(Object data) {
    	writeRowData(sheet.getLastRowNum() + 1, data);
    }
    
    /**
     * ����һ��ֵ������±�index(0��ʼ)���޸ĵ�index�����ݡ�
     * @param index
     * @param rowdata
     */
    public void writeRowData(int index, Object[] rowdata) {
    	Row row = sheet.createRow(index);   
        for(int i=0; i<rowdata.length; i++) {  
        	Cell cell = row.createCell(i);  
        	if (rowdata[i] instanceof Number) {
        		cell.setCellValue(((Number)rowdata[i]).doubleValue());
        	} else {
        		cell.setCellValue(rowdata[i].toString());  
        	}
        }
    }
    
    /**
     * ����һ��������±�index(0��ʼ)���޸ĵ�index�����ݡ�
     * @param index
     * @param data
     */
    public void writeRowData(int index, Object data) {
    	Class c = data.getClass();
    	Field[] fields = c.getDeclaredFields();
    	Object[] rowData = new Object[fields.length];
    	for (int i=0; i<fields.length; i++) {
    		fields[i].setAccessible(true);
    		try {
    			rowData[i] = fields[i].get(data);
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
    	}
    	writeRowData(index, rowData);
    }
    
    
    /**
     * ��list����д��һ���µ�excel
     * @param data
     * @param filePath
     */
    public static void writeFile(List data, String filePath) {
    	Workbook workbook = null;
    	String suffix = filePath.substring(filePath.lastIndexOf("."));
		if (".xls".equals(suffix)) {  
			workbook = new HSSFWorkbook();
			workbook.createSheet();
		} else if(".xlsx".equals(suffix)) {  
			workbook = new XSSFWorkbook();
			workbook.createSheet();
		} else {  
			System.out.println("��������ȷ���ļ�����(ֻ֧��xls��xlsx)");
		}
    	try {
    		FileOutputStream fileOut = null;
    		File f = new File(filePath);
    		if (!f.getParentFile().exists()) {
    			f.getParentFile().mkdirs();
    		}
			fileOut = new FileOutputStream(filePath);
			workbook.write(fileOut);
			fileOut.close();
			ExcelUtil eu = new ExcelUtil(filePath);
			for (int i=0; i<data.size(); i++) {
    			Object rowdata = data.get(i);
    			eu.writeRowData(i, rowdata);
    		}
			eu.write();
    	} catch (IOException e) {
    		e.printStackTrace();
    	}
    }
    
    public static void main(String args[]) throws IOException {
    	List data = new ArrayList<Test>();
    	for (int i=0; i<10; i++) {
    		data.add(new Test());
		}
    	ExcelUtil.writeFile(data, "D:\\hange_file\\test.xlsx");
    	ExcelUtil eu = new ExcelUtil("D:\\hange_file\\test.xlsx");
    	eu.writeRowData(20, new String[]{"1", "1"});
    	eu.write();
    	System.out.println(Arrays.toString(eu.readRowData(0)));
    }
    
}

class Test {
	private String a;
	public int b;
	protected double c;
	public int d;
	
	public Test() {
		a = "123����";
		b = 123;
		c = 123.456;
		d = 520;
	}
}
