package top.ablocker.maoexample.dao;

public abstract class ApiUse<T>
{
	public abstract void onSuccess(T result);
	public void onFail(Exception e) {}
}
