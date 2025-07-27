package com.erdal.mapper;

import java.util.Set;
import java.util.stream.Collectors;

import com.erdal.model.Category;
import com.erdal.modelDTO.BookingDTO;

public class BookingMapper {

	public static BookingDTO mapToCategoryDTO(Category category) {

		BookingDTO categoryDTO = new BookingDTO();
		categoryDTO.setId(category.getId());
		categoryDTO.setCategoryName(category.getCategoryName());
		categoryDTO.setImage(category.getImage());
		categoryDTO.setSaloonId(category.getSaloonId());
		

		return categoryDTO;

	}


	public static Set<BookingDTO> mapAllListToCategoriesDTO(Set<Category> categories) {
	    return categories.stream()
	        .map(BookingMapper::mapToCategoryDTO)
	        .collect(Collectors.toSet());
	}

//		
	
	

}
