package net.sf.uadetector.parser;

import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.regex.Pattern;

import net.sf.uadetector.ReadableUserAgent;
import net.sf.uadetector.UserAgentFamily;
import net.sf.uadetector.UserAgentStringParser;
import net.sf.uadetector.datareader.DataReader;
import net.sf.uadetector.datastore.DataStore;
import net.sf.uadetector.internal.data.Data;
import net.sf.uadetector.internal.data.domain.Browser;
import net.sf.uadetector.internal.data.domain.BrowserOperatingSystemMapping;
import net.sf.uadetector.internal.data.domain.BrowserPattern;
import net.sf.uadetector.internal.data.domain.BrowserType;
import net.sf.uadetector.internal.data.domain.OperatingSystem;
import net.sf.uadetector.internal.data.domain.OperatingSystemPattern;
import net.sf.uadetector.internal.data.domain.Robot;
import net.sf.uadetector.internal.util.RegularExpressionConverter;

import org.junit.Assert;
import org.junit.Test;

import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

public class AbstractUserAgentStringParserTest {

	@Test
	public void examineAsBrowser_noMatchingSubGroupToGatherVersionNumber() {
		// create data structures
		final Set<Browser> browsers = new HashSet<Browser>();
		final Map<Integer, SortedSet<BrowserPattern>> browserPatterns = Maps.newHashMap();
		final Map<Integer, BrowserType> browserTypes = Maps.newHashMap();
		final SortedMap<BrowserPattern, Browser> patternToBrowserMap = new TreeMap<BrowserPattern, Browser>();
		final Set<BrowserOperatingSystemMapping> browserToOperatingSystemMappings = Sets.newHashSet();
		final Set<OperatingSystem> operatingSystems = new HashSet<OperatingSystem>();
		final Map<Integer, SortedSet<OperatingSystemPattern>> operatingSystemPatterns = Maps.newHashMap();
		final SortedMap<OperatingSystemPattern, OperatingSystem> patternToOperatingSystemMap = new TreeMap<OperatingSystemPattern, OperatingSystem>();
		final List<Robot> robots = new ArrayList<Robot>();
		final String version = "test-version";

		// fill structures with data
		final TreeSet<BrowserPattern> browserPatternSet = Sets.newTreeSet();
		// a pattern without a subgroup definition to gather the version number
		final Pattern pattern = RegularExpressionConverter.convertPerlRegexToPattern("/^Eudora?/si ");
		final BrowserPattern browserPattern = new BrowserPattern(465, pattern, 439);
		browserPatternSet.add(browserPattern);
		browserPatterns.put(1, browserPatternSet);
		final BrowserType browserType = new BrowserType(2, "Browser");
		browserTypes.put(browserType.getId(), browserType);
		final Browser browser = new Browser(465, UserAgentFamily.EUDORA, "Eudora", new TreeSet<BrowserPattern>(), browserType, null,
				"eudora.png", "/list-of-ua/browser-detail?browser=Eudora", "Qualcomm Incorporated.", "http://www.qualcomm.com/",
				"http://www.eudora.com/archive.html");
		browsers.add(browser);
		patternToBrowserMap.put(browserPattern, browser);

		// create Data instance
		final Data data = new Data(browsers, browserPatterns, browserTypes, patternToBrowserMap, browserToOperatingSystemMappings,
				operatingSystems, operatingSystemPatterns, patternToOperatingSystemMap, robots, version);

		final UserAgentStringParser parser = new UserAgentStringParserImpl<DataStore>(new DataStore() {
			@Override
			public Charset getCharset() {
				return DataStore.DEFAULT_CHARSET;
			}

			@Override
			public Data getData() {
				return data;
			}

			@Override
			public DataReader getDataReader() {
				return null;
			}

			@Override
			public URL getDataUrl() {
				return null;
			}

			@Override
			public URL getVersionUrl() {
				return null;
			}
		});

		final ReadableUserAgent ua1 = parser.parse("Eudora");
		Assert.assertEquals(UserAgentFamily.EUDORA, ua1.getFamily());
		Assert.assertEquals("", ua1.getVersionNumber().toVersionString());

		final ReadableUserAgent ua2 = parser.parse("Eudora/1.0");
		Assert.assertEquals(UserAgentFamily.EUDORA, ua2.getFamily());
		Assert.assertEquals("", ua2.getVersionNumber().toVersionString());
	}

}
