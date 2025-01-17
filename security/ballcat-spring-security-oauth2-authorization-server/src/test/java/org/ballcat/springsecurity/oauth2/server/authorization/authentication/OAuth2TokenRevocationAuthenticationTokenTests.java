/*
 * Copyright 2023 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.ballcat.springsecurity.oauth2.server.authorization.authentication;

import org.ballcat.springsecurity.oauth2.server.authorization.TestOAuth2Authorizations;
import org.ballcat.springsecurity.oauth2.server.authorization.client.TestRegisteredClients;
import org.junit.jupiter.api.Test;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.security.oauth2.server.authorization.OAuth2Authorization;
import org.springframework.security.oauth2.server.authorization.OAuth2TokenType;
import org.springframework.security.oauth2.server.authorization.authentication.OAuth2ClientAuthenticationToken;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;

import java.time.Duration;
import java.time.Instant;
import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * Tests for {@link OAuth2TokenRevocationAuthenticationToken}.
 *
 * @author Vivek Babu
 * @author Joe Grandja
 */
class OAuth2TokenRevocationAuthenticationTokenTests {

	private String token = "token";

	private RegisteredClient registeredClient = TestRegisteredClients.registeredClient().build();

	private OAuth2ClientAuthenticationToken clientPrincipal = new OAuth2ClientAuthenticationToken(this.registeredClient,
			ClientAuthenticationMethod.CLIENT_SECRET_BASIC, this.registeredClient.getClientSecret());

	private String tokenTypeHint = OAuth2TokenType.ACCESS_TOKEN.getValue();

	private OAuth2AccessToken accessToken = new OAuth2AccessToken(OAuth2AccessToken.TokenType.BEARER, this.token,
			Instant.now(), Instant.now().plus(Duration.ofHours(1)));

	private OAuth2Authorization authorization = TestOAuth2Authorizations
		.authorization(this.registeredClient, this.accessToken, Collections.emptyMap())
		.build();

	@Test
	void constructorWhenTokenNullThenThrowIllegalArgumentException() {
		assertThatThrownBy(
				() -> new OAuth2TokenRevocationAuthenticationToken(null, this.clientPrincipal, this.tokenTypeHint))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessage("token cannot be empty");
	}

	@Test
	void constructorWhenClientPrincipalNullThenThrowIllegalArgumentException() {
		assertThatThrownBy(() -> new OAuth2TokenRevocationAuthenticationToken(this.token, null, this.tokenTypeHint))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessage("clientPrincipal cannot be null");
	}

	@Test
	void constructorWhenOAuth2AuthorizationNullThenThrowIllegalArgumentException() {
		assertThatThrownBy(
				() -> new OAuth2TokenRevocationAuthenticationToken(null, this.accessToken, this.clientPrincipal))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessage("authorization cannot be null");
	}

	@Test
	void constructorWhenRevokedTokenNullThenThrowIllegalArgumentException() {
		assertThatThrownBy(
				() -> new OAuth2TokenRevocationAuthenticationToken(this.authorization, null, this.clientPrincipal))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessage("revokedToken cannot be null");
	}

	@Test
	void constructorWhenRevokedTokenAndClientPrincipalNullThenThrowIllegalArgumentException() {
		assertThatThrownBy(
				() -> new OAuth2TokenRevocationAuthenticationToken(this.authorization, this.accessToken, null))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessage("clientPrincipal cannot be null");
	}

	@Test
	void constructorWhenTokenProvidedThenCreated() {
		OAuth2TokenRevocationAuthenticationToken authentication = new OAuth2TokenRevocationAuthenticationToken(
				this.token, this.clientPrincipal, this.tokenTypeHint);
		assertThat(authentication.getToken()).isEqualTo(this.token);
		assertThat(authentication.getPrincipal()).isEqualTo(this.clientPrincipal);
		assertThat(authentication.getTokenTypeHint()).isEqualTo(this.tokenTypeHint);
		assertThat(authentication.getCredentials().toString()).isEmpty();
		assertThat(authentication.isAuthenticated()).isFalse();
	}

	@Test
	void constructorWhenRevokedTokenProvidedThenCreated() {
		OAuth2TokenRevocationAuthenticationToken authentication = new OAuth2TokenRevocationAuthenticationToken(
				this.authorization, this.accessToken, this.clientPrincipal);
		assertThat(authentication.getAuthorization()).isEqualTo(this.authorization);
		assertThat(authentication.getAuthorization().getAccessToken().getToken()).isEqualTo(this.accessToken);
		assertThat(authentication.getToken()).isEqualTo(this.accessToken.getTokenValue());
		assertThat(authentication.getPrincipal()).isEqualTo(this.clientPrincipal);
		assertThat(authentication.getTokenTypeHint()).isNull();
		assertThat(authentication.getCredentials().toString()).isEmpty();
		assertThat(authentication.isAuthenticated()).isTrue();
	}

}
