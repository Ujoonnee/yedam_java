package com.yedam.java.example;

public abstract class Converter {

	protected double ratio;
	
	abstract protected double convert(double src);

	abstract protected String getSrcString();

	abstract protected String getDestString(); 

	public abstract void run();

}
