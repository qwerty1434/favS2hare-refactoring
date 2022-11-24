package com.favshare._temp.entity;

import javax.persistence.*;

import com.favshare.user.entity.User;
import lombok.*;

@Entity
@Table(name = "show_pop")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
@Builder
public class ShowPopEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "pop_id", nullable = false)
	private PopEntity popEntity;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id", nullable = false)
	private User user;

}
