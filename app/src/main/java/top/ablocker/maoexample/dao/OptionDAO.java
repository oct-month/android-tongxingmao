package top.ablocker.maoexample.dao;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import top.ablocker.maoexample.entity.Option;
import top.ablocker.maoexample.entity.Tag;

public class OptionDAO
{
	private OkHttpClient client = new OkHttpClient();
	private Gson gson = new Gson();

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
					if (responseJson.trim().equals(""))
					{
						apiUse.onFail();
						return;
					}
					List<Option> optionList = gson.fromJson(responseJson, new TypeToken<List<Option>>(){}.getType());
					apiUse.onSuccess(optionList);
				}
				catch (Exception e) {
					e.printStackTrace();
					apiUse.onFail();
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
					if (responseJson.trim().equals(""))
					{
						apiUse.onFail();
						return;
					}
					Option option = gson.fromJson(responseJson, Option.class);
					apiUse.onSuccess(option);
				}
				catch (Exception e) {
					e.printStackTrace();
					apiUse.onFail();
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
					if (responseJson.trim().equals(""))
					{
						apiUse.onFail();
						return;
					}
					Option option1 = gson.fromJson(responseJson, Option.class);
					apiUse.onSuccess(option1);
				}
				catch (Exception e) {
					e.printStackTrace();
					apiUse.onFail();
				}
			}
		}).start();
	}
}
