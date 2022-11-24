package kosta.mvc.util;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
 
	
/**
 * ���� ���ε�� �̸� ���� (�ߺ�����) Ŭ����
 * -> �������ϸ�+�����ð�+�ߺ�index�� ����
 * 
 * ���ε� �����̸� : �������ϸ�_yyyy-MM-dd_hhMMss_sss
 * �ߺ��� �����̸� : �������ϸ�_yyyy-MM-dd_hhMMss_sss(2~9999)
 * 
 * ��뿹��
 * 
 * File newFile = new FileRenameTimeIndex().rename(new File("��ι������̸�"));
 * 
 * String fileName=new FileRenameTimeIndex().rename(String path, String name);
 * 
 * @author ������
 *
 */
public class FileRenameTimeIndex {

	/**
	 * ������ �ִ��� Ȯ���ϱ�
	 * @param File f
	 * @return ������ true(��밡��), ������ false(���Ұ�)
	 */
	private boolean isNewFile(File f) { 
		try {
			return f.createNewFile(); //�����ϴ� ������ �ƴϸ� true(��������)
		}catch (IOException ignored) {
			return false;
		}
	} //isNewFile(File f) end
	
	/**
	 * ���� ������
	 * @param File f
	 * @return File f (�������� ����)
	 */
	public File renameFile(File f){//File f�� ���� ����
     
		String name = f.getName();
		String body = null;
		String ext = null;
	 
		int dot = name.lastIndexOf(".");
		if (dot != -1) {//Ȯ���ڰ� ������
		  body = name.substring(0, dot);
		  ext = name.substring(dot);
		} else { //Ȯ���ڰ� ������
			body = name;
			ext = "";
		} //Ȯ���� ����(else) ���ǹ� ��
 
		//�̸��� ���� �ð� ���ϱ�
		Date timestamp = new Date(System.currentTimeMillis()); //�ð� ���ϱ�(�и����������)
		SimpleDateFormat timeSdf = new SimpleDateFormat ("yyyy-MM-dd_hhMMss_sss"); //���˺���
	
		String newNameTime = body + "_" + timeSdf.format(timestamp)+ext; //�������ϸ�_�ð�+Ȯ����
		f = new File(f.getParent(), newNameTime);
		
		if(isNewFile(f)) { //�ߺ� ���ٸ� �ð��� ���̱�	
			
			return f;
			
		}else{ //�ߺ��� ������ ������ �����̸��ڿ� (2~9999)���� ���̱�
			
			int count=2;
			
			while (!isNewFile(f) && count < 9999) {
				
				count++;
				String newNameIndex = 
						body + "_" + timeSdf.format(timestamp) + "(" + count + ")" + ext;
				
				f = new File(f.getParent(), newNameIndex);
				
			}//while end
		}//NameIndex(else) end

		 return f;

	}//rename(File f) end
	
	///////////////////////////////////////////////////////////
	
	/**
	 * �̸��� �Է¹޾� ������ �ִ��� Ȯ���ϱ�
	 * @param String path
	 * @param String name
	 * @return ������ true(��밡��), ������ false(���Ұ�)
	 */
	private boolean isNewName(String path, String name) {
		File f=new File(path+"/"+name);
		try {
			return f.createNewFile(); //�����ϴ� ������ �ƴϸ� true(��������)
		}catch (IOException ignored) {
			return false;
		}
	} //isNewName(String path, String name) end
	
	/**
	 * rename���ϱ� (���+�̸��� �ޱ�)
	 * @param String file, String name
	 * @return String (�������� ���+�̸�)
	 */
	public String newNameOnly(String path, String name){
     
		String body = null;
		String ext = null;
	 
		int dot = name.lastIndexOf(".");
		if (dot != -1) {//Ȯ���ڰ� ������
		  body = name.substring(0, dot);
		  ext = name.substring(dot);
		} else { //Ȯ���ڰ� ������
			body = name;
			ext = "";
		} //Ȯ���� ����(else) ���ǹ� ��
 
		//�̸��� ���� �ð� ���ϱ�
		Date timestamp = new Date(System.currentTimeMillis()); //�ð� ���ϱ�(�и����������)
		SimpleDateFormat timeSdf = new SimpleDateFormat ("yyyy-MM-dd_hhMMss_sss"); //���˺���
	
		String newNameTime = body + "_" + timeSdf.format(timestamp)+ext; //�������ϸ�_�ð�+Ȯ����
		
		if(isNewName(path, newNameTime)==false) {
			//�ߺ��� ������ ������ �����̸��ڿ� (2~9999)���� ���̱�
			
			int count=2;
			
			while (isNewName(path, newNameTime) && count < 9999) {
				
				count++;
				
			}//while��
			
			String newNameIndex = 
					body + "_" + timeSdf.format(timestamp) + "(" + count + ")" + ext;
			
			return newNameIndex;
			
		}//if(isNewName(path, newNameTime)==false) end

		 return newNameTime;

	}//newNameOnly(String path, String name) end

}//class end