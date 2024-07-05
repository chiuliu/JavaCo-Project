package presentation.manage;

import business.entity.Category;
import business.feature.ICategory;
import business.feature.impl.CategoryImpl;
import business.feature.impl.TryCatchAll;

import java.util.Scanner;

public class CategoryManagement {
    public static ICategory categoryFeature = new CategoryImpl();
    


    public static void categoryMenu(Scanner scanner){
        boolean isExit = true;
        do {
            System.out.println("=================== CategoryManagement ===================");
            System.out.println("1. Hiển thị danh sách danh mục ");
            System.out.println("2. Thêm mới danh mục ");
            System.out.println("3. Cập nhật danh mục ");
            System.out.println("4. Xóa tác giả ");
            System.out.println("5. Thoát ");
            System.out.println("==========================================================");

            int choice = TryCatchAll.inputNumber(scanner);

            switch(choice){
                case 1:
                    showAll();
                    break;
                case 2:
                    addCategory(scanner);
                    break;
                case 3:
                    updateCategory(scanner);
                    break;
                case 4:
                    deleteCategory(scanner);
                    break;
                case 5:
                     isExit = false;
                    break;
                default:
                    System.err.println("Vui lòng nhập từ 1-5");
            }
            
        }
        while (isExit);

    }
    // Xóa danh mục

    private static void deleteCategory(Scanner scanner) {
        System.out.println("Nhập mã danh mục muốn xóa : ");
        int deleteId = TryCatchAll.inputNumber(scanner);
        categoryFeature.delete(deleteId);
    }
    // Cập nhật danh mục

    private static void updateCategory(Scanner scanner) {
        System.out.println("Mời bạn nhập mã danh mục : ");
        int idUpdate = TryCatchAll.inputNumber(scanner);
        int indexUpdate = categoryFeature.findIndexById(idUpdate);
        if(idUpdate >= 0){
            Category categoryUpdate = CategoryImpl.categoryList.get(indexUpdate);
                boolean isExit = true;
                do {
                    System.out.println("=============Update=============");
                    System.out.println("1. Cập nhật tên danh mục");
                    System.out.println("2. Cập nhật mô tả danh mục");
                    System.out.println("3. Trạng thái danh mục ");
                    System.out.println("4. Thoát");
                    System.out.println("================================");
                    System.out.println("Lựa chọn cập nhật của bạn : ");
                    int choice = TryCatchAll.inputNumber(scanner);
                    switch(choice){
                        case 1:
                            System.out.println("Tên danh mục muốn update : ");
                            categoryUpdate.setCategoryName(scanner.nextLine());
                            break;
                        case 2:
                            System.out.println("Mô tả danh mục muốn update : ");
                            categoryUpdate.setDescription(scanner.nextLine());
                            break;
                        case 3:
                            System.out.println("Trạng thái danh mục muốn update : ");
                            categoryUpdate.setStatus(Boolean.parseBoolean(scanner.nextLine()));
                            break;
                        case 4:
                            isExit = false;
                            break;
                        default:
                            System.err.println("Vui lòng nhập từ 1- 4");
                    }
                    categoryFeature.addOrUpdate(categoryUpdate);
                    
                }while (isExit);

        }
        else {
            System.err.println("Không tìm thấy danh mục" + indexUpdate);
        }
    }

    // Thêm danh mục
    private static void addCategory(Scanner scanner) {
        System.out.println("Nhập số lượng danh mục muốn thêm : ");
        int number = TryCatchAll.inputNumber(scanner);
        for (int i = 0; i < number; i++){
            Category newCategory = new Category();
            newCategory.inputCategory(scanner);
            categoryFeature.addOrUpdate(newCategory);

        }


    }


// Hiển thị tất cả danh mục
    private static void showAll() {
        if (CategoryImpl.categoryList.isEmpty()){
            System.err.println("Danh mục trống");
        }
        else {
            for (Category c : CategoryImpl.categoryList) {
                c.displayCategory();
            }
        }
    }


}
