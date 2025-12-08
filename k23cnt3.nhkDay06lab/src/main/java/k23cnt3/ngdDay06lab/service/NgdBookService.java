package k23cnt3.ngdDay06lab.service;

import k23cnt3.ngdDay06lab.entity.ngdBook;
import k23cnt3.ngdDay06lab.repository.NgdBookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class NgdBookService {
    @Autowired
    private NgdBookRepository ngdBookRepository;

    // Lấy toàn bộ danh sách các book
    public List<ngdBook> getAllNgdBooks(){
        return ngdBookRepository.findAll();
    }

    // Lấy 1 cuốn sách theo id
    public  ngdBook getNgdBookById(Long ngdId){
        return ngdBookRepository.findById(ngdId).orElse(null);
    }

    // Cập nhật thông tin sách
    public  ngdBook saveNgdBook(ngdBook ngdBook){
        return ngdBookRepository.save(ngdBook);
    }

    // xóa
    public  void  deleteNgdBook(Long ngdId){
        ngdBookRepository.deleteById(ngdId);
    }
}
