package com.thanosfisherman.elvis;

public class Main
{
    public static void main(String[] args)
    {
        Elvis.of(null).ifPresent(System.out::println);
    }
}
