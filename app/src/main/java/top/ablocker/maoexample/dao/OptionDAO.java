package top.ablocker.maoexample.dao;

import android.app.Activity;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import top.ablocker.maoexample.entity.Option;

public class OptionDAO
{
	private Activity context;
	private OkHttpClient client = new OkHttpClient();
	private Gson gson = new Gson();

	public OptionDAO(Activity context)
	{
		this.context = context;
	}

	// 获取所有option
	public void getAllOptions(ApiUse<List<Option>> apiUse)
	{
		new Thread(new Runnable() {
			@Override
			public void run()
			{
				try {
					Request request = new Request.Builder()
							.url(ApiConfig.BASE_URL + "/optionapi/options")
							.get()
							.build();
					Response response = client.newCall(request).execute();
					String responseJson = response.body().string();
					List<Option> optionList = gson.fromJson(responseJson, new TypeToken<List<Option>>(){}.getType());
					if (optionList == null)
					{
						throw new RuntimeException("查询返回结果为null");
					}
					context.runOnUiThread(new Runnable() {
						@Override
						public void run()
						{
							apiUse.onSuccess(optionList);
						}
					});

				}
				catch (Exception e) {
					context.runOnUiThread(new Runnable() {
						@Override
						public void run()
						{
							apiUse.onFail(e);
						}
					});
				}
			}
		}).start();
	}

	// 获取一个option
	public void getAnOption(int optionId, ApiUse<Option> apiUse)
	{
		new Thread(new Runnable() {
			@Override
			public void run()
			{
				try {
					Request request = new Request.Builder()
							.url(ApiConfig.BASE_URL + "/optionapi/option/" + optionId)
							.get()
							.build();
					Response response = client.newCall(request).execute();
					String responseJson = response.body().string();
					Option option = gson.fromJson(responseJson, Option.class);
					if (option == null || option.getId() <= 0)
					{
						throw new RuntimeException("查询返回结果为null");
					}
					context.runOnUiThread(new Runnable()
					{
						@Override
						public void run()
						{
							apiUse.onSuccess(option);
						}
					});
				}
				catch (Exception e) {
					context.runOnUiThread(new Runnable()
					{
						@Override
						public void run()
						{
							apiUse.onFail(e);
						}
					});
				}
			}
		}).start();
	}

	// 增加一个option
	public void addAnOption(Option option, ApiUse<Option> apiUse)
	{
		new Thread(new Runnable() {
			@Override
			public void run()
			{
				try {
					RequestBody requestBody = RequestBody.create(gson.toJson(option), ApiConfig.JSON);
					Request request = new Request.Builder()
							.url(ApiConfig.BASE_URL + "/optionapi/add")
							.post(requestBody)
							.build();
					Response response = client.newCall(request).execute();
					String responseJson = response.body().string();
					Option option1 = gson.fromJson(responseJson, Option.class);
					if (option1 == null || option1.getId() <= 0)
					{
						throw new RuntimeException("增加option失败");
					}
					context.runOnUiThread(new Runnable()
					{
						@Override
						public void run()
						{
							apiUse.onSuccess(option1);
						}
					});
				}
				catch (Exception e) {
					context.runOnUiThread(new Runnable()
					{
						@Override
						public void run()
						{
							apiUse.onFail(e);
						}
					});
				}
			}
		}).start();
	}
}
