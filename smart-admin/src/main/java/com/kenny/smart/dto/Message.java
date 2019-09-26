package com.kenny.smart.dto;

/**
 * ClassName: Message
 * Function:  数据传输模型DTO
 * Date:      2019/9/26 10:55
 * @author Kenny
 * version    V1.0
 */
public class Message {

    //TODO dto 类面想ui 作用一般在业务层（BeanUtils.copyProperties(dto, model);）转化为数据模型  ，
    // 优点当前端参数变化 ，后端只需做较小调整 ，一会有前端补上

    //TODO 一般应用系统直接用实体模型model作为表现层中的数据模型，这样看起来类少了很多，但是会有很多的问题
    // 1.如果实体模型发生变化，前端需要跟着改变
    // 2.方法签名不明确，一般service中方法参数都变成了实体模型，不看底层代码，很难判断具体有哪些数据为空，哪些字段有值，导致方法意义不明确
    // 一般建立数据传输模型DTO，用来做层与层时间的数据传递，DTO应当完全面向UI设计，没有行为属性。
    // 一般来说，数据模型model是面向业务的，数据传输模型DTO是面向UI的。这样我们可以把表现层和数据模型解耦，层与层之间调用更加意义明确。
    // 事物都有两面性，建立了DTO模型之后，会导致类大量增加，并且要进行DTO到model的映射。
}
