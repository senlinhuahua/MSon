package com.xsl.mson.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.netease.nimlib.sdk.AbortableFuture;
import com.netease.nimlib.sdk.NIMClient;
import com.netease.nimlib.sdk.Observer;
import com.netease.nimlib.sdk.RequestCallback;
import com.netease.nimlib.sdk.auth.AuthService;
import com.netease.nimlib.sdk.auth.LoginInfo;
import com.netease.nimlib.sdk.friend.FriendService;
import com.netease.nimlib.sdk.msg.MessageBuilder;
import com.netease.nimlib.sdk.msg.MsgService;
import com.netease.nimlib.sdk.msg.MsgServiceObserve;
import com.netease.nimlib.sdk.msg.constant.SessionTypeEnum;
import com.netease.nimlib.sdk.msg.model.IMMessage;
import com.xsl.mson.DemoCache;
import com.xsl.mson.R;
import com.xsl.mson.base.BaseActivity;
import com.xsl.mson.utils.string.MD5;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by forest on 2018/4/9 0009.
 */

public class LoginActivity extends BaseActivity {


    private AbortableFuture<LoginInfo> loginRequest;

    @BindView(R.id.loginid)
    EditText etLogin;
    @BindView(R.id.password)
    EditText etPassd;
    @BindView(R.id.tv_log)
    TextView log;
    @BindView(R.id.tv_incoming)
    TextView tvincoming;


    @Override
    protected void initView() {
//        if (loginRequest != null) {
//            loginRequest.abort();
//        }
    }

    @Override
    protected void initData() {
        etLogin.setText("forest");
        etPassd.setText("jkds");
        // 开启/关闭通知栏消息提醒

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    @OnClick({R.id.btn_login,R.id.btn_send,R.id.btsinglechat})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.btn_login:
                doLogin();
                doIncoming();
                break;
            case R.id.btn_send:
                doSendMessage();
                break;
            case R.id.btsinglechat:
                Intent intent = new Intent(this,SingleMessageActivity.class);

                startActivity(intent);
                break;

        }
    }

    private void doSendMessage() {
        //发送账号
        String account = "forest";
// 以单聊类型为例
        SessionTypeEnum sessionType = SessionTypeEnum.P2P;
        String text = etPassd.getText().toString();
// 创建一个文本消息
        IMMessage textMessage = MessageBuilder.createTextMessage(account, sessionType, text);

// 发送给对方
        NIMClient.getService(MsgService.class).sendMessage(textMessage, false);

    }

    public void doLogin() {
        // 云信只提供消息通道，并不包含用户资料逻辑。开发者需要在管理后台或通过服务器接口将用户帐号和token同步到云信服务器。
        // 在这里直接使用同步到云信服务器的帐号和token登录。
        // 这里为了简便起见，demo就直接使用了密码的md5作为token。
        // 如果开发者直接使用这个demo，只更改appkey，然后就登入自己的账户体系的话，需要传入同步到云信服务器的token，而不是用户密码。


        final String account = etLogin.getText().toString();
        //final String account = "xiodada";
        //final String token = tokenFromPassword("2020520");
        final String token = "2020520";
        // 登录
        loginRequest = login(new LoginInfo(account, token), new RequestCallback<LoginInfo>() {
            @Override
            public void onSuccess(LoginInfo param) {
                //LogUtil.i(TAG, "login success");
                log.setText(param.describeContents()+" "+param.getAccount()+" ,"+
                        param.getAppKey()+",,"+param.getToken()+",,"+param.valid());
                //onLoginDone();

                DemoCache.setAccount(account);
                //saveLoginInfo(account, token);

                // 初始化消息提醒配置
                //initNotificationConfig();

                // 进入主界面
                //MainActivity.start(LoginActivity.this, null);
                //finish();
            }

            @Override
            public void onFailed(int code) {
                if (code == 302 || code == 404) {
                    log.setText(""+code);
                    //Toast.makeText(LoginActivity.this, R.string.login_failed, Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(LoginActivity.this, "登录失败: " + code, Toast.LENGTH_SHORT).show();
                    log.setText(""+code);
                }
            }

            @Override
            public void onException(Throwable exception) {
                Toast.makeText(LoginActivity.this, "ff" ,Toast.LENGTH_LONG).show();

            }
        });




        NIMClient.toggleNotification(true);

        // 更新消息提醒配置 StatusBarNotificationConfig，以设置不响铃为例。
//        StatusBarNotificationConfig config = UserPreferences.getStatusConfig();
//        config.ring = true;
//        NIMClient.updateStatusBarNotificationConfig(config);

        // 以不接收testAccount帐号消息为例
        NIMClient.getService(FriendService.class).setMessageNotify("xiodada", true)
                .setCallback(new RequestCallback<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        log.setText("success");
                    }

                    @Override
                    public void onFailed(int i) {
                        log.setText(i+"");
                    }

                    @Override
                    public void onException(Throwable throwable) {

                    }
                });


    }

    private void doIncoming(){
        Observer<List<IMMessage>> incomingMessageObserver = new Observer<List<IMMessage>>() {
            @Override
            public void onEvent(List<IMMessage> messages) {
                // 处理新收到的消息，为了上传处理方便，SDK 保证参数 messages 全部来自同一个聊天对象。
                tvincoming.setText(messages.size()+" "+messages.get(messages.size()-1).getContent());
            }
        };
        NIMClient.getService(MsgServiceObserve.class)
                .observeReceiveMessage(incomingMessageObserver, true);

    }



    //DEMO中使用 username 作为 NIM 的account ，md5(password) 作为 token
    //开发者需要根据自己的实际情况配置自身用户系统和 NIM 用户系统的关系
    private String tokenFromPassword(String password) {
        String appKey = readAppKey(this);
        boolean isDemo = "5c918223866f12afe3a7eb5a83efac12".equals(appKey)
                || "fe416640c8e8a72734219e1847ad2547".equals(appKey);

        return isDemo ? MD5.getStringMD5(password) : password;
    }

    private static String readAppKey(Context context) {
        try {
            ApplicationInfo appInfo = context.getPackageManager().getApplicationInfo(context.getPackageName(), PackageManager.GET_META_DATA);
            if (appInfo != null) {
                return appInfo.metaData.getString("com.netease.nim.appKey");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static AbortableFuture<LoginInfo> login(LoginInfo loginInfo, final RequestCallback<LoginInfo> callback) {

        AbortableFuture<LoginInfo> loginRequest = NIMClient.getService(AuthService.class).login(loginInfo);
        loginRequest.setCallback(new RequestCallback<LoginInfo>() {
            @Override
            public void onSuccess(LoginInfo loginInfo) {
                //loginSuccess(loginInfo.getAccount());
                callback.onSuccess(loginInfo);
            }

            @Override
            public void onFailed(int code) {
                callback.onFailed(code);
            }

            @Override
            public void onException(Throwable exception) {
                callback.onException(exception);
            }
        });
        return loginRequest;
    }


}
