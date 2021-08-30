package com.example.test9.Controller;

import com.example.test9.Model.Category;
import com.example.test9.Service.CategoryServiceImpl;
import com.example.test9.response.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("categories")
public class CategoryController {
    @Autowired
    CategoryServiceImpl categoryService;
    @PostMapping
    public ResponseEntity<?> createCategory(@RequestBody Category category){
        if(category.getName().trim().isEmpty()){
            return new ResponseEntity<>(new ResponseMessage("The name is required!"), HttpStatus.OK);
        }
        categoryService.save(category);
        return new ResponseEntity<>(new ResponseMessage("Create category success!"),HttpStatus.OK);
    }
    @GetMapping
    public ResponseEntity<?> showListCategory(){
            List<Category> categoryList = categoryService.findAll();
            if(categoryList.isEmpty()){
                return new ResponseEntity<>(new ResponseMessage("List is empty"),HttpStatus.OK);
            }
            return new ResponseEntity<>(categoryList,HttpStatus.OK);
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> updateCategory(@PathVariable Long id, @RequestBody Category category){
        Optional<Category> category1 = categoryService.findById(id);

        if(!category1.isPresent()){
            return new ResponseEntity<>(new ResponseMessage("Object is not present"),HttpStatus.OK);
        }
        if(category.getName().trim().isEmpty()){
            return  new ResponseEntity<>(new ResponseMessage("The name is required"),HttpStatus.OK);
        }
        category1.get().setName(category.getName());
        category1.get().setDescription(category.getDescription());
        categoryService.save(category1.get());
        return new ResponseEntity<>(new ResponseMessage("Update success!"),HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> deleteCategory(@PathVariable Long id){
        Optional<Category> category = categoryService.findById(id);
        if(!category.isPresent()){
                return new ResponseEntity<>(new ResponseMessage("Delete no success!!"),HttpStatus.NOT_FOUND);
        }
        categoryService.delete(category.get().getId());
        return new ResponseEntity<>(new ResponseMessage("Delete success!"),HttpStatus.OK);
    }
}
