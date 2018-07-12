package DAO;


import model.Shopuser;

//DAO工厂类    直接调用以创建接口实例
public class DAOFactory {
    public static AppointmentDAO getAppointmentDAO(){
        return new AppointmentDAOImpl();
    }

    public static CarDAO getCarDAO(){
        return new CarDAOImpl();
    }

    public static CusowncarDAO getCusowncarDAO(){
        return new CusowncarDAOImpl();
    }

    public static CustomeruserDAO getCustometuserDAO(){
        return new CustomeruserDAOImpl();
    }

    public static ShopowncarDAO getShopowncarDAO(){
        return new ShopowncarDAOImpl();
    }

    public static ShopuserDAO getShopuserDAO(){ return new ShopuserDAOImpl(); }

    public  static  CususeraptDAO getCususeraptDAO(){return new CususeraptDAOImpl();}

    public static ViewrecordDAO getViewrecordDAO(){
        return new ViewrecordDAOImpl();
    }

    public static ShopaptDAO getShopaptDAO(){
        return new ShopaptDAOImpl();
    }

    public static CarinfoDAO getCarinfoDAO(){
        return new CarinfoDAOImpl();
    }

    public static CarviewDAO getCarviewDAO(){ return  new CarviewDAOImpl();}

}
//CarDAO cardao = DAOFactory.getCarDAO()