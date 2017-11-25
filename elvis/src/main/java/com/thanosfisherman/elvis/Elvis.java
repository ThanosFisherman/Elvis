package com.thanosfisherman.elvis;


import com.thanosfisherman.elvis.interfaces.Consumer;
import com.thanosfisherman.elvis.interfaces.Function;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Created by Thanos Psaridis on 19/9/2017.
 * Custom Elvis Operator an alternative to Optional Operator
 */

public final class Elvis<T>
{
    @Nullable private final T mObject;

    private Elvis(@Nullable T t)
    {
        mObject = t;
    }

    private Elvis()
    {
        mObject = null;
    }

    public static <T> Elvis<T> of(T t)
    {
        return new Elvis<>(t);
    }

    @NotNull
    public static <T> Elvis<T> ofNonNull(@NotNull T t)
    {
        return new Elvis<>(Objects.requireNonNull(t, "SHOULD NOT BE NULL"));
    }

    public <S> Elvis<S> next(@NotNull Function<? super T, ? extends S> plumber)
    {
        return new Elvis<>(mObject == null ? null : plumber.apply(mObject));
    }

    @Nullable
    public T get()
    {
        return mObject;
    }

    @NotNull
    public T orElse(@NotNull T other)
    {
        return mObject == null ? other : mObject;
    }

    public void ifPresent(@NotNull Consumer<? super T> consumer)
    {
        if (mObject != null)
            consumer.accept(mObject);
    }

    public boolean isPresent()
    {
        return this.mObject != null;
    }

    public static <T> Elvis<T> empty()
    {
        return new Elvis<>();
    }
}