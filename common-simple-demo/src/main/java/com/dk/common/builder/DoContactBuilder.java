package com.dk.common.builder;

/**
 * Created with MyNetty
 *
 * @author dk05408
 * @Description:
 * @create 2017-09-06 15:37
 **/
public class DoContactBuilder {

    private final int    age;
    private final int    safeID;
    private final String name;
    private final String address;


    public int getAge() {
        return age;
    }

    public int getSafeID() {
        return safeID;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public static class Builder {

        private  int    age = 0;
        private  int    safeID = 0;
        private  String name = null;
        private  String address = null;

        //构建的步骤
        public Builder (String name) {
            this.name = name;
        }

        public Builder age(int val) {
            age = val;
            return this;
        }

        public Builder safeID(int val) {
            safeID = val;
            return this;
        }

        public Builder address(String val) {
            address = val;
            return this;
        }

        public DoContactBuilder build() { // 构建，返回一个新对象
            return new DoContactBuilder(this);
        }
    }

    private DoContactBuilder(Builder b) {
        age = b.age;
        safeID = b.safeID;
        name = b.name;
        address = b.address;
    }

    public static void main(String[] args) {
        DoContactBuilder ddc = new Builder("Ace").age(10)
                .address("beijing").build();
        System.out.println("name=" + ddc.getName() + " age=" + ddc.getAge()
                + " address=" + ddc.getAddress());
    }
}
