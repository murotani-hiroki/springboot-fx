package com.mrtn.fx.web;

import javax.validation.GroupSequence;

/**
 * バリデーションの順序を制御するためのインターフェース
 */
public class ValidateGroups {

    public interface First{};
    public interface Second{};
    public interface Third{};

    @GroupSequence({First.class, Second.class, Third.class})
    public interface All{}
}
