<%@ taglib uri="/WEB-INF/webwidgets.tld" prefix="ww" %>


    <table class="examples">

      <tr>
        <td colspan="2">
          Simple map link (to my house) for each provider. Come down and visit me :-)
        </td>
      </tr>

      <tr>
        <td>
          <ww:map provider="streetmap.co.uk" latitude="51.5758" longitude="0.2064">Streetmap</ww:map><br/>
          <ww:map provider="multimap.com" latitude="51.5758" longitude="0.2064">Multimap</ww:map><br/>
          <ww:map provider="print-multimap.com" latitude="51.5758" longitude="0.2064">Multimap (Print)</ww:map><br/>
          <ww:map provider="google.com" latitude="51.5758" longitude="0.2064">Google Maps</ww:map><br/>        </td>
        <td>

<pre class="code"><ww:noEval>
  <ww:map provider="streetmap.co.uk" latitude="51.5758" longitude="0.2064">Streetmap</ww:map><br/>
  <ww:map provider="multimap.com" latitude="51.5758" longitude="0.2064">Multimap</ww:map><br/>
  <ww:map provider="print-multimap.com" latitude="51.5758" longitude="0.2064">Multimap (Print)</ww:map><br/>
  <ww:map provider="google.com" latitude="51.5758" longitude="0.2064">Google Maps</ww:map><br/>
</ww:noEval></pre>
        </td>
      </tr>

      <tr>
        <td colspan="2">
          Each of the map providers again, but this time only defining the map
          location <b>once</b>. Using mapDetails and mapLink instead of the map tag.
          This achieves the same result as the example above, but I think this version
          is a lot cleaner.
        </td>
      </tr>

      <tr>
        <td>
          <ww:mapDetails latitude="51.5758" longitude="0.2064">
            <ww:mapLink provider="streetmap.co.uk">streetmap</ww:mapLink><br/>
            <ww:mapLink provider="multimap.com">multimap</ww:mapLink><br/>
            <ww:mapLink provider="print-multimap.com">print</ww:mapLink><br/>
            <ww:mapLink provider="google.com">Google Maps</ww:mapLink><br/>
          </ww:mapDetails>
        </td>
        <td>

<pre class="code"><ww:noEval>
<ww:mapDetails latitude="51.5758" longitude="0.2064">
  <ww:mapLink provider="streetmap.co.uk">streetmap</ww:mapLink><br/>
  <ww:mapLink provider="multimap.com">multimap</ww:mapLink><br/>
  <ww:mapLink provider="print-multimap.com">print</ww:mapLink><br/>
  <ww:mapLink provider="google.com">Google Maps</ww:mapLink><br/>
</ww:mapDetails>
</ww:noEval></pre>
        </td>
      </tr>

      <tr>
        <td colspan="2">
          Using render=false, and then combining with the popup tag,
          to allow maps to popup in a new window.
        </td>
      </tr>

      <tr>
        <td>
          <ww:map latitude="51.5758" longitude="0.2064" provider="google.com" render="false">
            <ww:popup>Popup Google Map</ww:popup>
          </ww:map>
        </td>
        <td>
<pre class="code"><ww:noEval>
<ww:map latitude="51.5758" longitude="0.2064" provider="google.com" render="false">
  <ww:popup>Popup Google Map</ww:popup>
</ww:map>
</ww:noEval></pre>
        </td>
      </tr>

    </table>
