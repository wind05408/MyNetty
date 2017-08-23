package com.dk.common.demo;

/**
 * Created with MyNetty
 *
 * @author dk05408
 * @Description: 静态内部类
 * @create 2017-08-23 15:44
 **/
public class Singleton {
    private Singleton(){
    }

    private static class SingletonHolder{
        private static final Singleton instance = new Singleton();
    }

    public static final Singleton getInstance() {
        return SingletonHolder.instance;
    }
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public void printInfo() {
        System.out.println("the Id is " + id);
    }


    /**
     *  测试Singleton
     * @param args
     */
    public static void main(String[] args) {
        Singleton ts1 = Singleton.getInstance();
        ts1.setId(1L);
        Singleton ts2 = Singleton.getInstance();
        ts1.setId(2L);

        ts1.printInfo();
        ts2.printInfo();

        if(ts1 == ts2){
            System.out.println("创建的是同一个实例");
        }else{
            System.out.println("创建的不是同一个实例");
        }
    }


}
