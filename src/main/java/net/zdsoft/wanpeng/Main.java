package net.zdsoft.wanpeng;

import net.zdsoft.wanpeng.Task.LoginTask;
import net.zdsoft.wanpeng.config.Config;

import java.util.ArrayList;
import java.util.List;

/**
 * Hello world!
 */
public class Main {
    public static void main(String[] args) {
        Config wangyb = new Config();
        wangyb.setUserName("wangyb");
        wangyb.setPassword("wyb64867072");
//        wangyb.setAuthorization("eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ7XCJhcFVzZXJJZFwiOlwiOTQ2NDU2RDY5QjhENEVCMDgwNUM5OTIyQTUxOTQ0RERcIixcImFwQ29kZVwiOlwid2FuZ3liXCIsXCJwd2RIYXNoXCI6XCJmZTE0NTk2ODViYzg3NmZjMWJhYmVjMmI5ZGVlNDNkNlwiLFwic2FsdFwiOlwic3p4eVwifSIsInRpbWVzdGFtcCI6MTY0MDc2MDAwNzc3Mn0.1uQsiklifdgosQY0iNbKrFMtMkXHCE5K8-rIIzViP6_w31YGB5k7k1IKd8tdPi9zsJoeHirJl6rFKD3qE97ngA");

        Config liuyang = new Config();
        List<Config> configs = new ArrayList<Config>();
        configs.add(wangyb);
//        configs.add(liuyang);

        LoginTask loginTask = new LoginTask();
        loginTask.doLogin(configs);

//        AttendHistoryTask historyTask = new AttendHistoryTask();
//        historyTask.getAttendHistory();
//
//        AttendTask attendTask = new AttendTask();
//        attendTask.doCheck();

    }
}
