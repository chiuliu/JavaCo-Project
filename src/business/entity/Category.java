package business.entity;

import business.feature.impl.CategoryImpl;

import java.io.Serializable;
import java.util.Scanner;

public class Category implements Serializable {
    private int categoryId;
    private String categoryName;
    private String description;
    private boolean status;

    public Category() {
    }

    public Category(int categoryId, String categoryName, String description, boolean status) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.description = description;
        this.status = status;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isStatus() {
        return status;
    }

    public boolean getStatus(){
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public void inputCategory(Scanner scanner){
        this.categoryId = autoCategoryId();
        this.categoryName = inputCategoryName(scanner);
        this.description = inputDescription(scanner);
        this.status = inputStatus(scanner);
        

    }

    public boolean inputStatus(Scanner scanner) {
        System.out.println("Nhập trạng thái danh mục : ");
        do {
            String status = scanner.nextLine().toLowerCase();
            if(status.equals("true") || status.equals("false")) {
                return Boolean.parseBoolean(status);
            }else {
                System.err.println("Chỉ nhận trạng thái true hoặc false");
            }
        }while (true);
    }

    public String inputDescription(Scanner scanner) {
        System.out.println("Nhập mô tả danh mục : ");
        return scanner.nextLine();
    }

    public String inputCategoryName(Scanner scanner) {
        System.out.println("Nhập tên danh mục : ");
        do {
            String catalogName = scanner.nextLine();
            if(catalogName.trim().isEmpty()){
                System.err.println("Tên danh mục không được để trống");
            }else {
                boolean isExist = false;
                for(Category ca:CategoryImpl.categoryList){
                    if(ca.getCategoryName().equals(catalogName)){
                        isExist = true;
                        break;
                    }
                }
                if(isExist){
                    System.err.println("Tên danh mục đã tồn tại,vui lòng nhập lại");
                }else {
                    return catalogName;
                }
            }
        }while (true);
    }

    private int autoCategoryId() {
        int max = 0;
        for (Category c : CategoryImpl.categoryList) {
            if (c.getCategoryId() > max) {
                max = c.getCategoryId();
            }
        }
        return max + 1;


    }

    public void  displayCategory(){
        System.out.printf("Mã danh mục: %5d | Tên danh mục : %10s | Mô tả danh mục | %10s | Trạng thái danh mục : %10s \n",
                this.categoryId, this.categoryName, this.description, this.status?"Active":"InActive"
                );

    }


}


