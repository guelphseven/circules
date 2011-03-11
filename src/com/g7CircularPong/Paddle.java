package com.g7CircularPong;

public class Paddle {

	float size;//In degrees
	
	public Paddle(float initialSize)
	{
		size=initialSize;
	}
	
	public void shrink()
	{
		size*=0.95f;
	}
	
	public float getSize()
	{
		return size;
	}
	
	public float getLocation()
	{
		return (0);
	}
}
