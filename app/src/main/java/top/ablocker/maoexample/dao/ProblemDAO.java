package top.ablocker.maoexample.dao;

import android.app.Activity;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import top.ablocker.maoexample.entity.Problem;

public class ProblemDAO
{
	private Activity context;
	private OkHttpClient client = new OkHttpClient();
	private Gson gson = new Gson();

	public ProblemDAO(Activity context)
	{
		this.context = context;
	}

	// 获取所有problem
	public void getAllProblems(ApiUse<List<Problem>> apiUse)
	{
		new Thread(new Runnable() {
			@Override
			public void run()
			{
				try {
					Request request = new Request.Builder()
							.url(ApiConfig.BASE_URL + "/problemapi/problems")
							.get()
							.build();
					Response response = client.newCall(request).execute();
					String responseJson = response.body().string();
					List<Problem> problemList = gson.fromJson(responseJson, new TypeToken<List<Problem>>(){}.getType());
					if (problemList == null)
					{
						throw new RuntimeException("查询返回结果为null");
					}
					context.runOnUiThread(new Runnable() {
						@Override
						public void run()
						{
							apiUse.onSuccess(problemList);
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

	// 获取一个problem
	public void getAnProblem(int problemId, ApiUse<Problem> apiUse)
	{
		new Thread(new Runnable() {
			@Override
			public void run()
			{
				try {
					Request request = new Request.Builder()
							.url(ApiConfig.BASE_URL + "/problemapi/problem/" + problemId)
							.get()
							.build();
					Response response = client.newCall(request).execute();
					String responseJson = response.body().string();
					Problem problem = gson.fromJson(responseJson, Problem.class);
					if (problem == null || problem.getId() <= 0)
					{
						throw new RuntimeException("查询返回结果为null");
					}
					context.runOnUiThread(new Runnable() {
						@Override
						public void run()
						{
							apiUse.onSuccess(problem);
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

	// 增加一个problem
	public void addAnProblem(Problem problem, ApiUse<Problem> apiUse)
	{
		new Thread(new Runnable() {
			@Override
			public void run()
			{
				try {
					RequestBody requestBody = RequestBody.create(gson.toJson(problem), ApiConfig.JSON);
					Request request = new Request.Builder()
							.url(ApiConfig.BASE_URL + "/problemapi/add")
							.post(requestBody)
							.build();
					Response response = client.newCall(request).execute();
					String responseJson = response.body().string();
					Problem problem1 = gson.fromJson(responseJson, Problem.class);
					if (problem1 == null || problem1.getId() <= 0)
					{
						throw new RuntimeException("增加problem失败");
					}
					context.runOnUiThread(new Runnable() {
						@Override
						public void run()
						{
							apiUse.onSuccess(problem1);
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
}
