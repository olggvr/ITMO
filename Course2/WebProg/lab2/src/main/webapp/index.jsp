<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>Lab-2</title>
  <link rel="stylesheet" href="./styles/styles.css">
</head>
<body>

  <div class="header">
    <div class="info">Name: <br><b>Gavrilin Oleg</b></div>
    <div class="info">Group: <br><b>P3230</b></div>
    <div class="info">Variant: <br><b>555001</b></div>
  </div>

  <div class="params">
    <svg width="400" height="400" class="graph">
      <line x1="200" y1="0" x2="200" y2="400" stroke-width="2"></line>
      <line x1="0" y1="200" x2="400" y2="200" stroke-width="2"></line>

      <polygon points="200,0 195,10 205,10" fill="white"></polygon>
      <text x="210" y="10" font-family="Arial" font-size="14">Y</text>

      <polygon points="400,200 390,195 390,205" fill="white"></polygon>
      <text x="390" y="220" font-family="Arial" font-size="14">X</text>

      <line x1="195" y1="50" x2="205" y2="50" stroke-width="2"></line>
      <text x="215" y="55" font-family="Arial" font-size="14">R</text>
      <line x1="195" y1="125" x2="205" y2="125" stroke-width="2"></line>
      <text x="215" y="130" font-family="Arial" font-size="14">R/2</text>
      <line x1="195" y1="275" x2="205" y2="275" stroke-width="2"></line>
      <text x="215" y="280" font-family="Arial" font-size="14">-R/2</text>
      <line x1="195" y1="350" x2="205" y2="350" stroke-width="2"></line>
      <text x="215" y="355" font-family="Arial" font-size="14">-R</text>

      <line x1="275" y1="195" x2="275" y2="205" stroke-width="2"></line>
      <text x="270" y="220" font-family="Arial" font-size="14">R/2</text>
      <line x1="350" y1="195" x2="350" y2="205" stroke-width="2"></line>
      <text x="350" y="220" font-family="Arial" font-size="14">R</text>
      <line x1="125" y1="195" x2="125" y2="205" stroke-width="2"></line>
      <text x="120" y="220" font-family="Arial" font-size="14">-R/2</text>
      <line x1="50" y1="195" x2="50" y2="205" stroke-width="2"></line>
      <text x="50" y="220" font-family="Arial" font-size="14">-R</text>

      <polygon points="200,200 50,200 50,50 200,50" fill="blue" opacity="0.7" stroke="white"></polygon>
      <polygon points="200,200 275,200 200,275" fill="blue" opacity="0.7" stroke="white"></polygon>
      <path d="M 200,200 L 200,350 A 150,150 0 0 1 50,200 Z" fill="blue" opacity="0.7" stroke="white"></path>
    </svg>
  </div>

  <div class="inpR">
    <label>Радиус R</label>
    <div>
      <label><input type="radio" name="r" value="1">1</label>
      <label><input type="radio" name="r" value="1.5">1.5</label>
      <label><input type="radio" name="r" value="2">2</label>
      <label><input type="radio" name="r" value="2.5">2.5</label>
      <label><input type="radio" name="r" value="3">3</label>
    </div>
  </div>
  <div id="error" style="color: red; display: none;">Choose R</div>

  <script type="module" src="./js/sendData.js"></script>
  <script type="module" src="./js/main.js"></script>
</body>
</html>