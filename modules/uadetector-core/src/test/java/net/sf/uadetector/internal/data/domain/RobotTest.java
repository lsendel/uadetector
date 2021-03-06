/*******************************************************************************
 * Copyright 2012 André Rouél
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ******************************************************************************/
package net.sf.uadetector.internal.data.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import javax.annotation.Nonnull;

import net.sf.qualitycheck.exception.IllegalNegativeArgumentException;
import net.sf.qualitycheck.exception.IllegalNullArgumentException;
import net.sf.uadetector.UserAgentFamily;

import org.junit.Assert;
import org.junit.Test;

public class RobotTest {

	private static final class Blueprint {

		private UserAgentFamily family = UserAgentFamily.MJ12BOT;

		private String familyName = "test-family-name";

		private String icon = "test-icon-name";

		private int id = 1234;

		private String infoUrl = "test-info.url";

		private String name = "test-name";

		private String producer = "test-producer";

		private String producerUrl = "test-producer-url";

		private String userAgentString = "test-uas";

		@Nonnull
		public Robot build() {
			return new Robot(id, name, family, familyName, infoUrl, producer, producerUrl, userAgentString, icon);
		}

		public Blueprint family(final UserAgentFamily family) {
			this.family = family;
			return this;
		}

		public Blueprint familyName(final String familyName) {
			this.familyName = familyName;
			return this;
		}

		public Blueprint icon(final String icon) {
			this.icon = icon;
			return this;
		}

		public Blueprint id(final int id) {
			this.id = id;
			return this;
		}

		public Blueprint infoUrl(final String infoUrl) {
			this.infoUrl = infoUrl;
			return this;
		}

		public Blueprint name(final String name) {
			this.name = name;
			return this;
		}

		public Blueprint producer(final String producer) {
			this.producer = producer;
			return this;
		}

		public Blueprint producerUrl(final String producerUrl) {
			this.producerUrl = producerUrl;
			return this;
		}

		public Blueprint userAgentString(final String userAgentString) {
			this.userAgentString = userAgentString;
			return this;
		}

	}

	@Test
	public void equals_different_FAMILY() {
		final Robot a = new Blueprint().family(UserAgentFamily.GOOGLEBOT).build();
		final Robot b = new Blueprint().family(UserAgentFamily.YAHOO).build();
		assertFalse(a.equals(b));
		assertFalse(a.hashCode() == b.hashCode());
	}

	@Test
	public void equals_different_FAMILYNAME() {
		final Robot a = new Blueprint().familyName("f1").build();
		final Robot b = new Blueprint().familyName("f2").build();
		assertFalse(a.equals(b));
		assertFalse(a.hashCode() == b.hashCode());
	}

	@Test
	public void equals_different_ICON() {
		final Robot a = new Blueprint().icon("icon-name-1").build();
		final Robot b = new Blueprint().icon("icon-name-2").build();
		assertFalse(a.equals(b));
		assertFalse(a.hashCode() == b.hashCode());
	}

	@Test
	public void equals_different_ID() {
		final Robot a = new Blueprint().id(123).build();
		final Robot b = new Blueprint().id(987).build();
		assertFalse(a.equals(b));
		assertFalse(a.hashCode() == b.hashCode());
	}

	@Test
	public void equals_different_INFOURL() {
		final Robot a = new Blueprint().infoUrl("info-url-1").build();
		final Robot b = new Blueprint().infoUrl("info-url-2").build();
		assertFalse(a.equals(b));
		assertFalse(a.hashCode() == b.hashCode());
	}

	@Test
	public void equals_different_NAME() {
		final Robot a = new Blueprint().name("name-1").build();
		final Robot b = new Blueprint().name("name-2").build();
		assertFalse(a.equals(b));
		assertFalse(a.hashCode() == b.hashCode());
	}

	@Test
	public void equals_different_PRODUCER() {
		final Robot a = new Blueprint().producer("p1").build();
		final Robot b = new Blueprint().producer("p2").build();
		assertFalse(a.equals(b));
		assertFalse(a.hashCode() == b.hashCode());
	}

	@Test
	public void equals_different_PRODUCERURL() {
		final Robot a = new Blueprint().producerUrl("pu1").build();
		final Robot b = new Blueprint().producerUrl("pu2").build();
		assertFalse(a.equals(b));
		assertFalse(a.hashCode() == b.hashCode());
	}

	@Test
	public void equals_different_USERAGENTSTRING() {
		final Robot a = new Blueprint().userAgentString("uas.1").build();
		final Robot b = new Blueprint().userAgentString("uas.2").build();
		assertFalse(a.equals(b));
		assertFalse(a.hashCode() == b.hashCode());
	}

	@Test
	public void equals_identical() {
		final Robot a = new Blueprint().build();
		final Robot b = new Blueprint().build();
		assertEquals(a, b);
		assertTrue(a.hashCode() == b.hashCode());
	}

	@Test
	public void equals_null() {
		final Robot a = new Blueprint().build();
		assertFalse(a.equals(null));
	}

	@Test
	public void equals_otherClass() {
		final Robot a = new Blueprint().build();
		assertFalse(a.equals(""));
	}

	@Test
	public void equals_same() {
		final Robot a = new Blueprint().build();
		assertEquals(a, a);
		assertSame(a, a);
		assertTrue(a.hashCode() == a.hashCode());
	}

	@Test(expected = IllegalNullArgumentException.class)
	public void precondition_FAMILY() {
		new Blueprint().family(null).build();
	}

	@Test(expected = IllegalNullArgumentException.class)
	public void precondition_FAMILYNAME() {
		new Blueprint().familyName(null).build();
	}

	@Test(expected = IllegalNullArgumentException.class)
	public void precondition_ICON() {
		new Blueprint().icon(null).build();
	}

	@Test(expected = IllegalNegativeArgumentException.class)
	public void precondition_ID() {
		new Blueprint().id(-1).build();
	}

	@Test(expected = IllegalNullArgumentException.class)
	public void precondition_INFOURL() {
		new Blueprint().infoUrl(null).build();
	}

	@Test(expected = IllegalNullArgumentException.class)
	public void precondition_NAME() {
		new Blueprint().name(null).build();
	}

	@Test(expected = IllegalNullArgumentException.class)
	public void precondition_PRODUCER() {
		new Blueprint().producer(null).build();
	}

	@Test(expected = IllegalNullArgumentException.class)
	public void precondition_PRODUCERURL() {
		new Blueprint().producerUrl(null).build();
	}

	@Test(expected = IllegalNullArgumentException.class)
	public void precondition_USERAGENTSTRING() {
		new Blueprint().userAgentString(null).build();
	}

	@Test
	public void testGetters() {
		final int id = 12354;
		final String icon = "bunt.png";
		final String infoUrl = "http://programming-motherfucker.com/";
		final String name = "Programming, Motherfucker";
		final UserAgentFamily family = UserAgentFamily.GOOGLEBOT;
		final String familyName = UserAgentFamily.GOOGLEBOT.getName();
		final String producerUrl = "https://github.com/before";
		final String producer = "Our Values";
		final String userAgentString = "I'm a robot";
		final Robot r = new Robot(id, name, family, familyName, infoUrl, producer, producerUrl, userAgentString, icon);

		Assert.assertEquals(family, r.getFamily());
		Assert.assertEquals(familyName, r.getFamilyName());
		Assert.assertEquals("bunt.png", r.getIcon());
		Assert.assertEquals(12354, r.getId());
		Assert.assertEquals("http://programming-motherfucker.com/", r.getInfoUrl());
		Assert.assertEquals("Programming, Motherfucker", r.getName());
		Assert.assertEquals("Our Values", r.getProducer());
		Assert.assertEquals("https://github.com/before", r.getProducerUrl());
		Assert.assertEquals("I'm a robot", r.getUserAgentString());
	}

	@Test
	public void testToString() {
		// reduces some noise in coverage report
		final int id = 12354;
		final String icon = "bunt.png";
		final String infoUrl = "http://programming-motherfucker.com/";
		final String name = "Programming, Motherfucker";
		final UserAgentFamily family = UserAgentFamily.MJ12BOT;
		final String familyName = "Majestic-12";
		final String producerUrl = "https://github.com/before";
		final String producer = "Our Values";
		final String userAgentString = "I'm a robot";
		final Robot r = new Robot(id, name, family, familyName, infoUrl, producer, producerUrl, userAgentString, icon);
		Assert.assertEquals(
				"ReadableRobot [id=12354, name=Programming, Motherfucker, family=MJ12BOT, familyName=Majestic-12, infoUrl=http://programming-motherfucker.com/, producer=Our Values, producerUrl=https://github.com/before, userAgentString=I'm a robot, icon=bunt.png]",
				r.toString());
	}

}
