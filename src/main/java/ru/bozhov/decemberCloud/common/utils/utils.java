package ru.bozhov.decemberCloud.common.utils;

public class utils<T> {
    public static <T> T nvl(T... args){
        if(args.length==0)
            return null;

        for(T arg : args){
            if(arg!=null)
                return arg;
        }

        return null;
    }
}
