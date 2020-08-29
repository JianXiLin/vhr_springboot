package com.jianxilin.vhr_springboot.Utils;

import com.jianxilin.vhr_springboot.model.Hr;
import org.springframework.security.core.context.SecurityContextHolder;

public class HrUtils {

    public static Hr getCurrentHr(){
        return ((Hr) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
    }

}
