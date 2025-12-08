package k23cnt3.ngdDay06lab.service;




import k23cnt3.ngdDay06lab.entity.ngdAuthor;
import k23cnt3.ngdDay06lab.repository.NgdAuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class NgdAuthorService {

    @Autowired
    private NgdAuthorRepository ngdAuthorRepository;

    // Lấy toàn bộ danh sách tác giả
    public  List<ngdAuthor> getAllNgdAuthors(){
        return ngdAuthorRepository.findAll();
    }
    // lấy ra một tác giả
    public ngdAuthor getNgdAuthorById(long ngdId){
        return  ngdAuthorRepository.findById(ngdId).orElse(null);
    }

    // Cập nhât thông tin
    public ngdAuthor saveNgdAuthor(ngdAuthor ngdAuthor){
        return ngdAuthorRepository.save(ngdAuthor);
    }

    // xóa
    public  void deleteNgdAuthorById(long ngdId){
        ngdAuthorRepository.deleteById(ngdId);
    }

    public List<ngdAuthor> findNgdAuthorById(List<Long> ngdIds){
        return ngdAuthorRepository.findAllById(ngdIds);
    }
}
