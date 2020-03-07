package com.patterns.design.behavioral;

//Memento pattern is used to restore state of an object to a previous state. It is also known as snapshot pattern
//A memento is is like a restore point during the life cycle on the object, which the client application can use to restore the object state to its state
//The intent of memento pattern is to capture the internal state of an object without violating encapsulation and thus providing a mean for restoring the object into initial state when needed.
//Memento pattern shall be be used in any application in which object’s state is continuously changing and the user of the application may decide to rollback or undo the changes changes at any point.
//Memento pattern uses three actor classes. Memento contains state of an object to be restored. Originator creates and stores states in Memento objects and Caretaker object is responsible to restore object state from Memento
//needs to take care is that Memento class should be accessible only to the Originator object
//One of the drawback is that if Originator object is very huge then Memento object size will also be huge and use a lot of memory.

public class MementoDesignPatternTester {

	public static void main(String[] args) {
		FileWriterCaretaker caretaker = new FileWriterCaretaker();
		
		FileWriterUtil fileWriter = new FileWriterUtil("data.txt");
		fileWriter.write("First Set of Data\n");
		System.out.println(fileWriter+"\n\n");
		
		// lets save the file
		caretaker.save(fileWriter);
		//now write something else
		fileWriter.write("Second Set of Data\n");
		
		//checking file contents
		System.out.println(fileWriter+"\n\n");

		//lets undo to last save
		caretaker.undo(fileWriter);
		
		//checking file content again
		System.out.println(fileWriter+"\n\n");
	}

}

class FileWriterUtil {

	private String fileName;
	private StringBuilder content;
	
	public FileWriterUtil(String file){
		this.fileName=file;
		this.content=new StringBuilder();
	}
	
	@Override
	public String toString(){
		return this.content.toString();
	}
	
	public void write(String str){
		content.append(str);
	}
	
	public Memento save(){
		return new Memento(this.fileName,this.content);
	}
	
	public void undoToLastSave(Object obj){
		Memento memento = (Memento) obj;
		this.fileName= memento.fileName;
		this.content=memento.content;
	}
	
	
	private class Memento{
		private String fileName;
		private StringBuilder content;
		
		public Memento(String file, StringBuilder content){
			this.fileName=file;
			//notice the deep copy so that Memento and FileWriterUtil content variables don't refer to same object
			this.content=new StringBuilder(content);
		}
	}
}

class FileWriterCaretaker {

	private Object obj;
	
	public void save(FileWriterUtil fileWriter){
		this.obj=fileWriter.save();
	}
	
	public void undo(FileWriterUtil fileWriter){
		fileWriter.undoToLastSave(obj);
	}
}