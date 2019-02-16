$(document).on('click', '.button_tab', function (e) {
    var $this = $(this),
        $type = $this.attr('data-type'),
        $direction = $this.attr('data-direction'),
        $ship = $this.parents('.ship'),
        $tabs = $ship.find('.button_tab.select[data-direction=' + $direction + ']'),
        $panels = $ship.find('.panel_' + $direction + '.show'),
        $info = $ship.find('.panel_' + $direction + '[data-type=' + $type + ']');

    for (var i = 0; i < $tabs.length; i++) {
        $tabs.eq(i).removeClass('select');
    }
    $this.addClass('select');

    for (var i = 0; i < $panels.length; i++) {
        $panels.eq(i).removeClass('show').addClass('hide');
    }
    $info.removeClass('hide').addClass('show');
});

$(document).on('click', '.button_module', function(){
    var $this = $(this),
        $index = parseInt($this.attr('data-index')),
        $type = $this.attr('data-type'),
        $pos = parseInt($this.attr('data-position')),
        $prevType = $this.attr('data-prev-type'),
        $prevPos = parseInt($this.attr('data-prev-position')),
        $ship = $this.parents('.ship'),
        $all = $ship.find('.button_module.select'),
        $modules = $ship.find('.button_module.select[data-index=' + $index + ']');

    if ($this.hasClass('select')) {
        return false;
    }

    for (var i = 0; i < $all.length; i++) {
        var $cur = $all.eq(i),
            $curType = $cur.attr('data-type'),
            $curPos = parseInt($cur.attr('data-position')),
            $curPrevType = $cur.attr('data-prev-type'),
            $curPrevPos = parseInt($cur.attr('data-prev-position'));

        if ((($curPrevType === $type && $curPrevPos > $pos) || ($prevType === $curType && $prevPos > $curPos)) && $curType !== $type) {
            return false;
        }
    }

    for (var i = 0; i < $modules.length; i++) {
        $modules.eq(i).removeClass('select');
    }
    $this.addClass('select');

    delayCall($ship);
});

$(document).on('click', '.button_upgrade', function (e) {
    e.stopPropagation();
    var $this = $(this),
        $index = parseInt($this.attr('data-index')),
        $ship = $this.parents('.ship'),
        $upgrades = $ship.find('.button_upgrade[data-index=' + $index + ']');

    if (!$this.hasClass('select')) {
        for (var i = 0; i < $upgrades.length; i++) {
            $upgrades.eq(i).removeClass('select');
            $upgrades.eq(i).addClass('hide');
        }
        $this.addClass('select');
        $this.removeClass('hide');
        delayCall($ship);
    } else {
        $upgrades.removeClass('hide');
    }
});

$(document).on('click', '.button_consumable', function (e) {
    e.stopPropagation();
    var $this = $(this),
        $index = parseInt($this.attr('data-index')),
        $ship = $this.parents('.ship'),
        $consumables = $ship.find('.button_consumable[data-index=' + $index + ']');

    if (!$this.hasClass('select')) {
        for (var i = 0; i < $consumables.length; i++) {
            $consumables.eq(i).removeClass('select');
            $consumables.eq(i).addClass('hide');
        }
        $this.addClass('select');
        $this.removeClass('hide');
    } else {
        $consumables.removeClass('hide');
    }
});

$(document).on('click', function () {
    var $upgrades = $('.button_upgrade'),
        $consumables = $('.button_consumable');

    for (var i = 0; i < $upgrades.length; i++) {
        if (!$upgrades.eq(i).hasClass('select')) {
            $upgrades.eq(i).addClass('hide');
        }
    }

    for (var i = 0; i < $consumables.length; i++) {
        if (!$consumables.eq(i).hasClass('select')) {
            $consumables.eq(i).addClass('hide');
        }
    }
});

var $maxSpts = 19;
$(document).on('click', '.button_skill', function () {
    var $this = $(this),
        $index = parseInt($this.attr('data-index')),
        $pos = parseInt($this.attr('data-position')),
        $ship = $this.parents('.ship'),
        $pts = $ship.find('.points'),
        $skills = $ship.find('.button_skill.select'),
        $totalSpts = 0;

    var canUse = true;
    var hasIndex = [false, false, false, false];

    if (!$this.hasClass('select')) {
        hasIndex[$index] = true;
        $totalSpts = $totalSpts + $index + 1;
    }

    for (var i = 0; i < $skills.length; i++) {
        var $sIndex = parseInt($skills.eq(i).attr('data-index'));
        var $sPos = parseInt($skills.eq(i).attr('data-position'));

        if ($sIndex !== $index || $sPos !== $pos) {
            $totalSpts = $totalSpts + $sIndex + 1;
            hasIndex[$sIndex] = true;
        }
    }

    for (var i = 3; i > 0; i--) {
        if (hasIndex[i] && !hasIndex[i - 1]) {
            canUse = false;
            break;
        }
    }

    if (!canUse || $totalSpts > $maxSpts) {
        return false;
    }

    if ($this.hasClass('select')) {
        $this.removeClass('select');
    } else {
        $this.addClass('select');
    }

    $pts.text($totalSpts);

    delayCall($ship);
});

$(document).on('click', '.switch', function (e) {
    var $this = $(this),
        $type = $this.attr('data-type'),
        $toggle = $this.parent().find('.' + $type);

    for (var i = 0; i < $toggle.length; i++) {
        if ($toggle.eq(i).hasClass('toggle')) {
            if ($toggle.eq(i).hasClass('hide')) {
                $toggle.eq(i).removeClass('hide');
            } else {
                $toggle.eq(i).addClass('hide');
            }
        }
    }
});

var waitTime = 1500;
var timer = [];
function delayCall($ship)
{
    var index = $ship.attr('data-ship-index');
    window.clearTimeout(timer[index]);
    timer[index] = window.setTimeout(function() {
        callPage($ship);
    }, waitTime);
}

function callPage($ship)
{
    var sModules = $ship.find('.button_module.select');
    var sUpgrades = $ship.find('.button_upgrade.select');
    var sSkills = $ship.find('.button_skill.select');
    var $toggle = $ship.find('.toggle');
    var $toggleDecide = {};

    for (var i = 0; i < $toggle.length; i++) {
        if ($toggle.eq(i).hasClass('hide')) {
            $toggleDecide[$toggle.eq(i).attr('class').replace('toggle', '').replace('hide', '').trim()] = 'hide';
        } else {
            $toggleDecide[$toggle.eq(i).attr('class').replace('toggle', '').trim()] = '';
        }
    }

    var modules = '';
    var mArrays = [0, 0, 0, 0, 0, 0, 0, 0, 0, 0];
    for (var i = 0; i < sModules.length; i++) {
        mArrays[sModules.eq(i).attr('data-index')] = parseInt(sModules.eq(i).attr('data-position')) + parseInt(sModules.eq(i).attr('data-temp-position'));
    }
    for (var x in mArrays) {
        modules += (mArrays[x] === 0 ? '' : mArrays[x].toString());
    }

    var upgrades = '';
    var uArrays = [0, 0, 0, 0, 0, 0];
    for (var i = 0; i < sUpgrades.length; i++) {
        uArrays[sUpgrades.eq(i).attr('data-index')] = parseInt(sUpgrades.eq(i).attr('data-position'));
    }
    for (var x in uArrays) {
        upgrades += (uArrays[x] === 0 ? '0' : uArrays[x].toString());
    }

    var skills = 0;
    for (var i = 0; i < sSkills.length; i++) {
        var pos = parseInt(sSkills.eq(i).attr('data-index')) * 8 + parseInt(sSkills.eq(i).attr('data-position'));
        skills += Math.pow(2, pos);
    }

    var $shipIndex = $ship.attr('name');
    var url = '/ship?index=' + $shipIndex
            + (modules !== '' ? '&modules=' + modules : '')
            + (upgrades.replace('0') !== '' ? '&upgrades=' + upgrades : '')
            + (skills > 0 ? '&skills=' + skills.toString() : '')
            + '&lang=' + lang;

    $.ajax({
        url: url,
        type: 'post',
        success: function (data) {
            if (data.status === undefined) {
                $('.info_box_inner.replace').remove();
                $('.info_box').append(data);

                for (var x in $toggleDecide) {
                    var temp = '.toggle.' + x.toString();
                    if ($toggleDecide[x] === 'hide') {
                        $('[name=' + $shipIndex + ']').find(temp).addClass('hide')
                    } else {
                        $('[name=' + $shipIndex + ']').find(temp).removeClass('hide')
                    }
                }

                history.replaceState({
                    id: $shipIndex
                }, '', url);
            } else {
                console.log(data);
            }
        },
        error: function (data) {
            console.log(data)
        }
    })
}