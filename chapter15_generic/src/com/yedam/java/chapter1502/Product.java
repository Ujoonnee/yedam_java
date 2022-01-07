package com.yedam.java.chapter1502;

public class Product<T, M> {
	
	private T kind;
	private M model;
	
	public void setKind(T kind) {
		this.kind = kind;
	}
	
	public void setModel(M model) {
		this.model = model;
	}
	
	public T getKind() {
		return kind;
	}
	
	public M getModel() {
		return model;
	}
	
	
}
