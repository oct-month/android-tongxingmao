package top.ablocker.maoexample.dao;

public interface ApiUse<T>
{
	public void onSuccess(T result);
	public void onFail();
}
