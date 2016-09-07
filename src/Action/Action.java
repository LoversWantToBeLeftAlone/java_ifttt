package Action;

public interface Action {   
    /**  
     * 所有的具体Action实现这个接口  
     * @param request 请求对象  
     * @param response 应答对象  
     * @return ：结果页面  
     */  
    public String 
    execute(javax.servlet.http.HttpServletRequest request,javax.servlet.http.HttpServletResponse response);   
       
}  