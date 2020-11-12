package com.wechat.model.dao.crm.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.wechat.model.bean.Image;
import com.wechat.model.dao.crm.ImageDao;


public class ImageDaoImpl implements ImageDao {
	/**
	 * 统计条数
	 */
	@Override
	public int getTotal() {
		int total = 0;
        try {
        	//加载驱动
            Class.forName("com.mysql.jdbc.Driver");
 
            //连接数据库
            Connection c = DriverManager.getConnection("jdbc:mysql://39.97.237.226:3306/wxos?characterEncoding=UTF-8",
                    "root", "root");
            //数据库语句加载器
            Statement s = c.createStatement();
            //数据库语句
            String sql = "select count(*) from image";
            //数据库语句执行
            ResultSet rs = s.executeQuery(sql);
            //得到结果
            while (rs.next()) {
            	//取得是第一个字段的数量
                total = rs.getInt(1);
            }
//            System.out.println("total:" + total);
            s.close();
            c.close();
 
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return total;
	}
	

	@Override
	public List<Image> list() {
		return list(0, Short.MAX_VALUE);
	}

	@Override
	public List<Image> list(int start, int count) {
		//这个集合存所有的数据
		List<Image> images = new ArrayList<Image>(); 
		try {
			//加载驱动
			Class.forName("com.mysql.jdbc.Driver"); 
			Connection c = DriverManager.getConnection("jdbc:mysql://39.97.237.226:3306/wxos?characterEncoding=UTF-8",
				    "root", "root");
			//查询语句 在一页能有多少条数据   limit去限制条数 desc递减
			String sql = "select * from image order by id limit ?, ?"; 
			PreparedStatement ps = c.prepareStatement(sql); 
			
			ps.setInt(1, start);
			ps.setInt(2,  count);
			//查询总数  返回结果集合
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				//创建对象
				Image image = new Image(); 
				//从结果集合中获取值
				int id = rs.getInt("id"); 
				String theme = rs.getString("theme");
				//将值设置进对象中
				image.setId(id);
				image.setTheme(theme);
				//将对象设置进一个集合中
				images.add(image);
			}
			System.out.println(images);
			//关闭连接
			ps.close(); 
			c.close();
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		//返回所有数据集合
		return images;
	}

	@Override
	public void add(Image image) {
		try {
            Class.forName("com.mysql.jdbc.Driver");
 
            Connection c = DriverManager.getConnection("jdbc:mysql://39.97.237.226:3306/wxos?characterEncoding=UTF-8",
                    "root", "root");
            String sql = "insert into image values(null,?)";
            //预编译    这个主键自增必须要数据库的id也是自动递增的，这一点要记住
            PreparedStatement ps = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);  //主键自增
            ps.setString(1, image.getTheme()); //参数设值
 
            //执行sql语句
            ps.execute();
            //通常我们在应用中对mysql执行了insert操作后，需要获取插入记录的自增主键，这时候通常用getGeneratedKeys()方法获取主键
            ResultSet rs = ps.getGeneratedKeys();  //结果集合主键自增
            if (rs.next()) {
            	//
                int id = rs.getInt(1);
                image.setId(id);  //为对象设置主键id
            }
            ps.close();
            c.close();
 
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
	}


	@Override
	public void delete(int id) {
		try {
            Class.forName("com.mysql.jdbc.Driver");
 
            Connection c = DriverManager.getConnection("jdbc:mysql://39.97.237.226:3306/wxos?characterEncoding=UTF-8",
                    "root", "root");
            String sql = "delete from image where id = ?";  //根据id删除指定的数据
            //预编译
            PreparedStatement ps = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            //设置参数
            ps.setInt(1, id);
 
            //执行删除sql语句
            ps.execute();  
            
            ps.close();
            c.close();
 
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
	}
	//输入查询到内容  返回结果集合对象
	@Override
	public List<Image> query(String query) {
		List<Image> images = new ArrayList<Image>(); 
		try {
            Class.forName("com.mysql.jdbc.Driver");
           //创建连接
            Connection c = DriverManager.getConnection("jdbc:mysql://39.97.237.226:3306/wxos?characterEncoding=UTF-8",
                    "root", "root");
            String sql = "select * from image where theme like '%" + query + "%'";  //模糊查询指定字段
            //预编译
            Statement s = c.createStatement(); 
            //调用sql语句
            ResultSet rs = s.executeQuery(sql); 
            while(rs.next()) {
            	Image image = new Image(); 
            	Integer id = rs.getInt("id");  //得到查询结果中相应的id 
            	String theme = rs.getString("theme"); 
            	image.setId(id);  //为对象赋值
            	image.setTheme(theme);
            	images.add(image);  //这里将单个对象添加进集合中
            }
            s.close();
            c.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
		return images;  //返回图片集合对象
	}  
}
