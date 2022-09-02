package com.example.userservice.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.example.userservice.client.OrderServiceClient;
import feign.FeignException;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.userservice.dto.UserDto;
import com.example.userservice.jpa.UserEntity;
import com.example.userservice.jpa.UserRepository;
import com.example.userservice.vo.ResponseOrder;
import org.springframework.web.client.RestTemplate;

@Service
@Transactional(readOnly = true)
@Slf4j
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepository userRepository;

	@Autowired
	BCryptPasswordEncoder passwordEncoder;

	@Autowired
	RestTemplate restTemplate;

	@Autowired
	OrderServiceClient orderServiceClient;

	@Autowired
	Environment env;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserEntity userEntity = userRepository.findByEmail(username);
		if (userEntity == null)
			throw new UsernameNotFoundException(username + ": not found");
		return new User(userEntity.getEmail(), userEntity.getEncryptedPwd(),
				new ArrayList<>());
	}
		
	@Override
	@Transactional
	public UserDto createUser(UserDto userDto) {
		userDto.setUserId(UUID.randomUUID().toString());
		
		ModelMapper mapper = new ModelMapper();
		mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		UserEntity userEntity = mapper.map(userDto, UserEntity.class);
		userEntity.setEncryptedPwd(passwordEncoder.encode(userDto.getPwd()));
		userRepository.save(userEntity);
		
		UserDto resUserDto = mapper.map(userEntity, UserDto.class);
		return resUserDto;
	}
	
	@Override
	public UserDto getUserByUserId(String userId) {
		UserEntity userEntity = userRepository.findByUserId(userId);

        UserDto userDto = new ModelMapper().map(userEntity, UserDto.class);
//        List<ResponseOrder> orderList = new ArrayList<>();
		/** RestTemplate을 사용하여 동기방식 통신
		String orderUrl = String.format(env.getProperty("order_service.url"), userId);
		ResponseEntity<List<ResponseOrder>> orderListResponse =
				restTemplate.exchange(orderUrl, HttpMethod.GET, null,
						new ParameterizedTypeReference<List<ResponseOrder>>() {});
		List<ResponseOrder> orderList = orderListResponse.getBody();
		*/

		// OpenFeign 사용
		List<ResponseOrder> orderList = null;
		try {
			orderList = orderServiceClient.getOrders(userId);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}

        userDto.setOrders(orderList);
		return userDto;
	}
	
	@Override
	public Iterable<UserEntity> getUserByAll() {		
		return userRepository.findAll();
	}

	@Override
	public UserDto getUserDetailsByEmail(String email) {
		UserEntity userEntity = userRepository.findByEmail(email);
		UserDto userDto = new ModelMapper().map(userEntity, UserDto.class);
		return userDto;
	}
}
