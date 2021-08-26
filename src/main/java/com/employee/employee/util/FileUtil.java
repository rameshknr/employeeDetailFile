package com.employee.employee.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.StringTokenizer;

import javax.swing.JOptionPane;

import org.springframework.stereotype.Component;

import com.employee.employee.pojo.Employee;

@Component
public class FileUtil {

	public static List<Employee>  formatCsvtoPojo() throws IOException {
		
		File file = new File("./employee.csv");
		if(file.exists()){
		 System.out.println("File Exists");
		}
		BufferedReader bufRdr = new BufferedReader(new FileReader(file));
		List<Employee> employeeList = new ArrayList<>();
		String line = null;
		int row =0,column =0;
		try {
			while((line = bufRdr.readLine()) != null){
				if(row != 0) {
			    StringTokenizer st = new StringTokenizer(line,",");
			    column = 0 ;
			    Employee employee = new Employee();
			    while (st.hasMoreTokens()){
			    	
			    	if(column ==0 ) {
			    		employee.setId(Integer.parseInt(st.nextToken()));
			    	}else if(column ==1 ) {
			    		employee.setName(st.nextToken());
			    	}else if(column ==2 ) {
			    		employee.setAge(Integer.parseInt(st.nextToken()));
			    	}else if(column ==3 ) {
			    		employee.setExperience(Integer.parseInt(st.nextToken()));
			    	}else if(column ==4 ) {
			    		String stringdoj = st.nextToken();
			    		Date doj=new SimpleDateFormat("yyyy/MM/dd").parse(stringdoj);
			    		employee.setDoj(doj);
			    	}	    	
			    column ++;
			    }
			    employeeList.add(employee);
				}
				row++;
			}
			return employeeList;
		} catch (IOException | ParseException e) {
			e.printStackTrace();
			return null;
		}finally {
			bufRdr.close();
		}
		    
	}
	
	public static void addingDataToExcelSheet(List<Employee> employeeList) throws IOException {
	
		File file = new File("./employee.csv");
	    String data = null;
	        
	        if (file != null)
	        {
	            try
	            {
	                    BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file,false));
	                    PrintWriter fileWriter = new PrintWriter(bufferedWriter);
	                    fileWriter.print("ID"+",");
	                    fileWriter.print("NAME"+",");
	                    fileWriter.print("AGE"+",");
	                    fileWriter.print("EXPERIENCE"+",");
	                    fileWriter.print("DOJ"+",");
                        fileWriter.println("");

	                    for(int i=0; i <employeeList.size(); ++i)
	                    {
	                            for(int j=0; j < 5; ++j)
	                            {
	                            		if (j ==0 ) {
	                            	    	data = Integer.toString(employeeList.get(i).getId());
	                            	    }else if(j ==1) {
	                            	    	data = employeeList.get(i).getName();	
	                            	    }else if(j ==2) {
	                            	    	data = Integer.toString(employeeList.get(i).getAge());	
	                            	    }else if(j ==3) {
	                            	    	data = Integer.toString(employeeList.get(i).getExperience());	
	                            	    }else if(j ==4) {
	                            	    	SimpleDateFormat newFormat = new SimpleDateFormat("MM-dd-yyyy");
	                            	    	data = newFormat.format(employeeList.get(i).getDoj());
	                            	    }
	                            fileWriter.print(data+",");
	                            }
	                            fileWriter.println("");
	                    }       
	                    fileWriter.close();
	        }
	        catch(Exception e)
	        {
	                    JOptionPane.showMessageDialog(null, "Error "+e);
	        }
	    }     
    }
	
	public static void generateSheetSpecific(List<Employee> employeeList) throws IOException {
		
		File file = new File("./genericSheet.csv");
	    String data = null;
	        
	        if (file != null)
	        {
	            try
	            {
	                    BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file,false));
	                    PrintWriter fileWriter = new PrintWriter(bufferedWriter);
	                    fileWriter.print("ID"+",");
	                    fileWriter.print("NAME"+",");
	                    fileWriter.print("AGE"+",");
	                    fileWriter.print("EXPERIENCE"+",");
	                    fileWriter.print("DOJ"+",");
                        fileWriter.println("");
	                    for(int i=0; i <employeeList.size(); ++i)
	                    {
	                            for(int j=0; j < 5; ++j)
	                            {
	                            		if (j ==0 ) {
	                            	    	data = Integer.toString(employeeList.get(i).getId());
	                            	    }else if(j ==1) {
	                            	    	data = employeeList.get(i).getName();	
	                            	    }else if(j ==2) {
	                            	    	data = Integer.toString(employeeList.get(i).getAge());	
	                            	    }else if(j ==3) {
	                            	    	data = Integer.toString(employeeList.get(i).getExperience());	
	                            	    }else if(j ==4) {
	                            	    	SimpleDateFormat newFormat = new SimpleDateFormat("MM-dd-yyyy");
	                            	    	data = newFormat.format(employeeList.get(i).getDoj());
	                            	    }
	                            fileWriter.print(data+",");
	                            }
	                            fileWriter.println("");
	                    }       
	                    fileWriter.close();
	        }
	        catch(Exception e)
	        {
	                    JOptionPane.showMessageDialog(null, "Error "+e);
	        }
	    }     
    }
}
