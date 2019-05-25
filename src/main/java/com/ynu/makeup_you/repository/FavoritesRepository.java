package com.ynu.makeup_you.repository;

import com.ynu.makeup_you.entity.CommDoubleKey;
import com.ynu.makeup_you.entity.Favorites;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created on 2019/5/15
 * BY hujianlong
 */
public interface FavoritesRepository extends JpaRepository<Favorites, CommDoubleKey> {
    public List<Favorites> findByUserID(String uid);
    public List<Favorites> findByPostID(String postid);
}
