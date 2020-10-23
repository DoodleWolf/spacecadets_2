import java.util.*;
import java.io.*;

public class BareBones{
	Hashtable<String, Integer> variables;

	public BareBones(String file) throws IOException{
		variables = new Hashtable<String, Integer>();
		File txt = new File(file);
		Scanner scnr = new Scanner(txt);
		String cmdstxt = new String();
		while(scnr.hasNextLine()){
			cmdstxt += scnr.nextLine();
		}
		String[] cmds = cmdstxt.split(";");
		run(cmds);
	}

	public void run(String[] cmds){
		for(int i = 0; i < cmds.length;i++){
			checkCmd(cmds[i],i,cmds);
			System.out.println(variables);
		}
	}

	public void checkCmd(String cmdLine,int i,String[] cmds){
		String cmd = cmdLine.split(" ")[0];
		switch(cmd){
			case "clear":
				clear(cmdLine);
				break;
			case "incr":
				incr(cmdLine);
				break;
			case "decr":
				decr(cmdLine);
				break;
			case "while":
				mywhile(cmdLine,i,cmds);
				break;
			case "end":
				break;
			default:
				break;
		}
	}

	public void clear(String cmdLine){
		String varName = cmdLine.split(" ")[1];
		variables.put(varName,0);
	}

	public void incr(String cmdLine){
		String varName = cmdLine.split(" ")[1];
		variables.put(varName,variables.get(varName)+1);
	}

	public void decr(String cmdLine){
		String varName = cmdLine.split(" ")[1];
		variables.put(varName,variables.get(varName)-1);
	}

	public void mywhile(String cmdLine, int i,String[] cmds){
		String varName = cmdLine.split(" ")[1];
		int index = i+1;
		String whileCmds = new String();
		while(!(cmds[index].equals("end"))){
			whileCmds = whileCmds + cmds[index].substring(3,cmds[index].length()) + ";";
			index++;
		}
		String[] whileCmdArr = whileCmds.split(";");
		while(!(variables.get(varName) == Integer.parseInt(cmdLine.split(" ")[3]))){
			run(whileCmdArr);
		}
	}
	
	public static void main(String[] args ) throws IOException{
		BareBones cmd1 = new BareBones("cmd.txt");
	}
}
