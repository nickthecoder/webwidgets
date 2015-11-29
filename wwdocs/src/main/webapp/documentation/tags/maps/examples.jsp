<%@ taglib uri="http://nickthecoder.co.uk/webwidgets" prefix="ww" %>


<h3>Example 1</h3>
<p>
  Simple map link (to my house) for each provider. Come down and visit me :-)
</p>
  <ww:map provider="streetmap.co.uk" latitude="51.5758" longitude="0.2064">Streetmap</ww:map><br/>
  <ww:map provider="multimap.com" latitude="51.5758" longitude="0.2064">Multimap</ww:map><br/>
  <ww:map provider="print-multimap.com" latitude="51.5758" longitude="0.2064">Multimap (Print)</ww:map><br/>
  <ww:map provider="google.com" latitude="51.5758" longitude="0.2064">Google Maps</ww:map><br/>        </td>

<pre class="code"><ww:noEval>
  <ww:map provider="streetmap.co.uk" latitude="51.5758" longitude="0.2064">Streetmap</ww:map><br/>
  <ww:map provider="multimap.com" latitude="51.5758" longitude="0.2064">Multimap</ww:map><br/>
  <ww:map provider="print-multimap.com" latitude="51.5758" longitude="0.2064">Multimap (Print)</ww:map><br/>
  <ww:map provider="google.com" latitude="51.5758" longitude="0.2064">Google Maps</ww:map><br/>
</ww:noEval></pre>

<h3>Example 2</h3>
<p>
  Each of the map providers again, but this time only defining the map
  location <b>once</b>. Using mapDetails and mapLink instead of the map tag.
  This achieves the same result as the example above, but I think this version
  is a lot cleaner.
</p>
  <ww:mapDetails latitude="51.5758" longitude="0.2064">
    <ww:mapLink provider="streetmap.co.uk">streetmap</ww:mapLink><br/>
    <ww:mapLink provider="multimap.com">multimap</ww:mapLink><br/>
    <ww:mapLink provider="print-multimap.com">print</ww:mapLink><br/>
    <ww:mapLink provider="google.com">Google Maps</ww:mapLink><br/>
  </ww:mapDetails>

<pre class="code"><ww:noEval>
<ww:mapDetails latitude="51.5758" longitude="0.2064">
  <ww:mapLink provider="streetmap.co.uk">streetmap</ww:mapLink><br/>
  <ww:mapLink provider="multimap.com">multimap</ww:mapLink><br/>
  <ww:mapLink provider="print-multimap.com">print</ww:mapLink><br/>
  <ww:mapLink provider="google.com">Google Maps</ww:mapLink><br/>
</ww:mapDetails>
</ww:noEval></pre>

<h3>Example 3</h3>
<p>
  Using render=false, and then combining with the popup tag,
  to allow maps to popup in a new window.
</p>
  <ww:map latitude="51.5758" longitude="0.2064" provider="google.com" render="false">
    <ww:popup>Popup Google Map</ww:popup>
  </ww:map>

<pre class="code"><ww:noEval>
<ww:map latitude="51.5758" longitude="0.2064" provider="google.com" render="false">
  <ww:popup>Popup Google Map</ww:popup>
</ww:map>
</ww:noEval></pre>
