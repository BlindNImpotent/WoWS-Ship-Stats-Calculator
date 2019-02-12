function drawAirDefense(currentIndex, airDefense)
{
    if (airDefense !== null) {
        var aa = [airDefense.auraFar, airDefense.auraMedium, airDefense.auraNear];

        var airDefenseCanvas = currentIndex.find('[data-type=airDefenseCanvas]');
        var ctx = airDefenseCanvas[0].getContext('2d');

        var mid = 100;
        var size = 80;
        ctx.save();
        ctx.scale(1, 1);
        ctx.beginPath();
        ctx.globalAlpha = 0.5;
        ctx.arc(mid, mid, size, 0, 2 * Math.PI, false);
        ctx.closePath();
        ctx.fillStyle = '#64C8FF';
        ctx.fill();
        ctx.restore();

        ctx.beginPath();
        ctx.moveTo(mid, mid);
        ctx.globalAlpha = 1;
        ctx.arc(mid, mid, size, (90 / 180 * Math.PI), (270 / 180 * Math.PI));
        ctx.closePath();
        ctx.stroke();

        var furthest = -1;
        var closest = -1;

        var angles = airDefense.sectors;

        for (var i = 0; i < aa.length; i++) {
            if (aa[i] !== null) {
                var current = aa[i];
                var maxDist = current.maxDistance;
                var minDist = current.minDistance;

                if (maxDist > furthest) {
                    furthest = maxDist;
                }
                if (closest === -1 || closest > minDist) {
                    closest = minDist;
                }
            }
        }

        for (var i = 0; i < aa.length; i++) {
            if (aa[i] !== null) {
                var current = aa[i];
                var maxDist = current.maxDistance;
                var minDist = current.minDistance;

                for (var j = 0; j < 2; j++) {
                    var angle1 = angles[j][0] + 90;
                    var angle2 = angle1 + angles[j][1] + (-j * 180) + 90;

                    ctx.beginPath();
                    ctx.moveTo(mid, mid);
                    ctx.globalAlpha = 1;
                    ctx.arc(mid, mid, size * (maxDist / furthest), (angle1 / 180 * Math.PI), (angle2 / 180 * Math.PI));
                    // ctx.closePath();
                    ctx.stroke();

                    ctx.beginPath();
                    ctx.moveTo(mid, mid);
                    ctx.globalAlpha = 1;
                    ctx.arc(mid, mid, size * (minDist / furthest), (angle1 / 180 * Math.PI), (angle2 / 180 * Math.PI));
                    // ctx.closePath();
                    ctx.stroke();
                }
            }
        }
    }

    $('[data-script=airDefenseSector]').remove();
}