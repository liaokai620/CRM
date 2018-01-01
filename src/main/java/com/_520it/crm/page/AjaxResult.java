package com._520it.crm.page;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AjaxResult {

    private boolean success = true;
    private String  msg;

    public AjaxResult(boolean success, String msg) {
        super();
        this.success = success;
        this.msg = msg;
    }

    public AjaxResult(String msg) {
        super();
        this.msg = msg;
    }
}
