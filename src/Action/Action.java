package Action;

public interface Action {   
    /**  
     * ���еľ���Actionʵ������ӿ�  
     * @param request �������  
     * @param response Ӧ�����  
     * @return �����ҳ��  
     */  
    public String 
    execute(javax.servlet.http.HttpServletRequest request,javax.servlet.http.HttpServletResponse response);   
       
}  