package com.favshare._temp.service;

import com.favshare._temp.dto.input.FeedUserIdDto;
import com.favshare._temp.dto.input.IdFeedImageUrlDto;
import com.favshare._temp.dto.input.IdNameDto;
import com.favshare._temp.entity.FeedEntity;
import com.favshare.user.entity.User;
import com.favshare._temp.repository.FeedRepository;
import com.favshare.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FeedService {

	@Autowired
	private FeedRepository feedRepository;

	@Autowired
	private UserRepository userRepository;

	public void insertFeed(int userId) {
		FeedEntity feedEntity = new FeedEntity();
		User user = userRepository.findById(userId).get();
		if (feedRepository.countFeedByUserId(userId) == 0) {
			feedEntity = FeedEntity.builder().name("피드").isFirst(true).feedImageUrl(null).user(user)
					.build();
		} else {
			feedEntity = FeedEntity.builder().name("피드").isFirst(false).feedImageUrl(null).user(user)
					.build();
		}
		feedRepository.save(feedEntity);
	}

	public void deleteFeed(int feedId) {
		feedRepository.deleteById(feedId);
	}

	public void updateFeedName(IdNameDto idNameDto) {
		FeedEntity feedEntity;
		feedEntity = feedRepository.findById(idNameDto.getId()).get();
		feedEntity.changeName(idNameDto.getName());
		feedRepository.save(feedEntity);
	}

	public void updateFeedImage(IdFeedImageUrlDto idFeedImageUrlDto) {
		FeedEntity feedEntity;
		feedEntity = feedRepository.findById(idFeedImageUrlDto.getId()).get();
		feedEntity.changeImageUrl(idFeedImageUrlDto.getFeedImageUrl());
		feedRepository.save(feedEntity);
	}

	public void updateFirstFeed(FeedUserIdDto feedUserIdDto) {
		FeedEntity newFeedEntity, oldFeedEntity;
		newFeedEntity = feedRepository.findById(feedUserIdDto.getFeedId()).get();
		newFeedEntity.changeIsFirst();

		int oldFirstFeedId = feedRepository.findFirstId(feedUserIdDto.getUserId());
		oldFeedEntity = feedRepository.findById(oldFirstFeedId).get();
		oldFeedEntity.changeIsNotFirst();

		feedRepository.save(newFeedEntity);
		feedRepository.save(oldFeedEntity);
	}

}
