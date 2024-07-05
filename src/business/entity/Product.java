package business.entity;

import business.feature.impl.CategoryImpl;
import business.feature.impl.ProductImpl;
import business.feature.impl.TryCatchAll;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.UUID;

public class Product {
    private int productId;
    private String productName;
    private String sku;
    private String description;
    private float price;
    private int quantity;
    private String image;
    private Category category;
    private Date created;
    private Date updated;


    public Product() {
    }

    public Product(int productId, String productName, String sku, String description, float price, int quantity, String image, Category category, Date created, Date updated) {
        this.productId = productId;
        this.productName = productName;
        this.sku = sku;
        this.description = description;
        this.price = price;
        this.quantity = quantity;
        this.image = image;
        this.category = category;
        this.created = created;
        this.updated = updated;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    // InputProduct

    public void inputProduct(Scanner scanner){
        this.productId = autoIncreasing();
        this.productName = inputProductName(scanner);
        this.sku = autoSku();
        this.description = description(scanner);
        this.price = inputPrice(scanner);
        this.quantity = quantity(scanner);
        this.category = inputCategory(scanner);

    }

    public Category inputCategory(Scanner scanner) {
        for (Category c : CategoryImpl.categoryList) {
            System.out.printf("Mã danh mục : %10d | Tên danh mục : %15s",
                    c.getCategoryId(), c.getCategoryName());
        }
            System.out.println("Nhập mã danh mục để thêm sản phẩm : ");
            do {
                int choice = TryCatchAll.inputNumber(scanner);
                int findId = findCategoryIndexById(choice);
                if(findId >= 0){
                    return CategoryImpl.categoryList.get(findId);
                }
                else {
                    System.err.println("Không tìm thấy mã danh mục");;
                }

            }while (true);
    }

    public int findCategoryIndexById(int findId) {
        for (int i = 0; i < CategoryImpl.categoryList.size(); i++) {
            if(CategoryImpl.categoryList.get(i).getCategoryId() == findId) {
                return i;
            }
        }
        return -1;
    }

    public int quantity(Scanner scanner) {
        System.out.println("Nhập số lượng sản phẩm : ");
        do {
            String quantity = scanner.nextLine();
            if (quantity.trim().isEmpty()) {
                System.err.println("Số lượng không được để trống,vui lòng nhập lại");
            } else {
                if (Integer.parseInt(quantity) < 0) {
                    System.err.println("Số lượng phải >= 0, vui lòng nhập lại");
                } else {
                    return Integer.parseInt(quantity);
                }
            }
        } while (true);
    }



    public float inputPrice(Scanner scanner) {
        System.out.println("Nhập giá sản phẩm : ");
        do {
            String price = scanner.nextLine();
            if (price.trim().isEmpty()) {
                System.err.println("Giá sản phẩm không được để trống, vui lòng nhập lại ");
            } else {
                if (Float.parseFloat(price) < 0) {
                    System.err.println("Giá phải > 0, vui lòng nhập lại ");
                } else {
                    return Float.parseFloat(price);
                }
            }
        } while (true);
    }

    public String description(Scanner scanner) {
        System.out.println("Nhập mô tả sản phẩm : ");
        return scanner.nextLine();
    }

    public String autoSku() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString();
    }

    public String inputProductName(Scanner scanner) {
        System.out.println("Nhập tên sản phẩm : ");
        do {
            String nameProduct = scanner.nextLine();
            if (nameProduct.trim().isEmpty()) {
                System.err.println("Không được để trống ");
            } else {
                boolean isExist = false;
                for (Product p : ProductImpl.productList) {
                    if (p.getProductName().equals(nameProduct)) {
                        isExist = true;
                        break;
                    }
                }
                if (isExist) {
                    System.err.println("Tên sản phẩm đã tồn tại,vui lòng nhập lại ");
                } else {
                    return nameProduct;
                }
            }
        } while (true);
    }

    public int autoIncreasing() {
        int max = 0;
        for (Product p : ProductImpl.productList) {
            if (p.getProductId() > max) {
                max = p.getProductId();
            }
        }
        return max + 1;
    }

    public void displayProduct(){
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        System.out.printf(" Mã sản phẩm : %10d | Tên sản phẩm : %15s | Mã Code : %15s Mô tả : 15%s | Danh muc : 20s \n  ",
                this.productId,this.productName, this.sku , this.description, this.category.getCategoryName());
        System.out.printf("Tồn kho : %10d | Giá : %10f | Thời gian : %20s  ", this.quantity,this.price, sdf.format(this.created));
    }



}
