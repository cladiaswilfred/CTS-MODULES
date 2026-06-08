console.log("EventHub Portal initialized")
window.addEventListener('load',function(){
    bootApp()
})

const listContainer = document.querySelector('#listContainer')
const bookingForm = document.getElementById('bookingForm')
let allEvents = []
let totalSignups = 0

class Gathering{
    constructor(id,name,date,place,kind,cost,pic,desc){
        this.id=id
        this.name=name
        this.date=date
        this.place=place
        this.kind=kind
        this.cost=cost
        this.pic=pic
        this.desc=desc
        this.taken=0
        this.limit=50
    }
    isOpen(){return this.taken<this.limit}
}

async function loadData(){
    if(listContainer){
        listContainer.innerHTML='<div class="w-full text-center py-5"><div class="spinner-border text-primary" role="status"></div><p class="mt-2 text-secondary">Loading...</p></div>'
    }
    try{
        let resp=await fetch('https://jsonplaceholder.typicode.com/posts?_limit=6')
        await resp.json()

        let mockSet=[
            {id:1,name:"Neon Nights",date:"2026-07-20",place:"Bay Park",kind:"music",cost:28,pic:"https://picsum.photos/seed/g1/400/300",desc:"Live bands and neon lights under the stars.",gone:false},
            {id:2,name:"Bass Arena",date:"2026-08-15",place:"Stadium East",kind:"music",cost:42,pic:"https://picsum.photos/seed/g2/400/300",desc:"Top DJs spinning all night long.",gone:false},
            {id:3,name:"Street Eats",date:"2026-06-22",place:"Central Plaza",kind:"food",cost:10,pic:"https://picsum.photos/seed/g3/400/300",desc:"Food trucks and local flavors everywhere.",gone:false},
            {id:4,name:"Art Stroll",date:"2026-09-05",place:"Arts District",kind:"art",cost:18,pic:"https://picsum.photos/seed/g4/400/300",desc:"A walk through galleries and murals.",gone:false},
            {id:5,name:"City Marathon",date:"2026-10-12",place:"Downtown Core",kind:"sports",cost:30,pic:"https://picsum.photos/seed/g5/400/300",desc:"Run through the heart of the city.",gone:false},
            {id:6,name:"Dev Summit",date:"2026-11-08",place:"Tech Hub",kind:"tech",cost:55,pic:"https://picsum.photos/seed/g6/400/300",desc:"Hands-on workshops and networking.",gone:false},
            {id:7,name:"Old Meetup",date:"2023-01-01",place:"Town Hall",kind:"music",cost:0,pic:"https://picsum.photos/seed/g7/400/300",desc:"Archive event from last year.",gone:true}
        ]

        allEvents=mockSet.map(function(e){
            return new Gathering(e.id,e.name,e.date,e.place,e.kind,e.cost,e.pic,e.desc)
        })

        allEvents.forEach(function(ev,i){
            ev.gone=mockSet[i].gone
            if(i===1)ev.taken=50
        })

        let onlyMusic=allEvents.filter(function(e){return e.kind==='music'})
        console.log("Music events:",onlyMusic)

        renderEvents(allEvents)
    }catch(err){
        console.error("Failed to load:",err)
    }
}

function renderEvents(list){
    if(!listContainer)return
    listContainer.innerHTML=''

    let available=list.filter(function(ev){
        if(ev.gone)return false
        if(!ev.isOpen())return false
        return true
    })

    let mapped=available.map(function(ev){
        return Object.assign({},ev,{
            label:ev.kind.toUpperCase()+' // '+ev.name
        })
    })

    mapped.forEach(function(ev){
        let col=document.createElement('div')
        col.className='event-card'

        col.innerHTML='<img src="'+ev.pic+'" alt="'+ev.name+'">'
            +'<div class="event-body">'
            +'<span class="cat-tag">'+ev.kind+'</span>'
            +'<h5>'+ev.label+'</h5>'
            +'<p class="meta">'
            +'<span><i class="bi bi-calendar"></i> '+ev.date+'</span>'
            +'<span><i class="bi bi-geo-alt"></i> '+ev.place+'</span>'
            +'<span><i class="bi bi-tag"></i> $'+ev.cost+'</span>'
            +'</p>'
            +'<button class="btn btn-outline w-full" onclick="openDetail('+ev.id+')">Details</button>'
            +'</div>'

        listContainer.appendChild(col)
    })
}

function bootApp(){
    loadData()
    wireUp()
    restoreState()
}

function openDetail(id){
    let event=allEvents.find(function(e){return e.id===id})
    if(!event)return

    let{name,pic,kind,cost,desc,date,place}=event

    console.log("Event dump:")
    Object.keys(event).forEach(function(k){
        console.log(k+': '+event[k])
    })

    document.getElementById('modalTitle').innerText=name
    document.getElementById('modalImg').src=pic
    document.getElementById('modalCat').innerText=kind.toUpperCase()
    document.getElementById('modalFee').innerText=cost===0?'Free':'$'+cost
    document.getElementById('modalDesc').innerText=desc
    document.getElementById('modalDate').innerText=date
    document.getElementById('modalLoc').innerText=place

    document.getElementById('detailModal').classList.add('show')
}

function closeModal(id){
    document.getElementById(id).classList.remove('show')
}

function goBooking(){
    closeModal('detailModal')
    document.getElementById('signupSection').scrollIntoView({behavior:'smooth'})
}

function applyFilter(cb){
    let val=document.getElementById('catFilter').value
    localStorage.setItem('savedCategory',val)

    let filtered=allEvents
    if(val!=='all'){
        filtered=allEvents.filter(function(e){return e.kind===val})
    }

    if(typeof cb==='function'){
        cb(filtered)
    }else{
        renderEvents(filtered)
        try{$('#listContainer').hide().fadeIn(400)}catch(e){}
    }
}

var counter=(function(){
    var counts={music:0,sports:0,tech:0,free:0,art:0}
    return{
        add:function(k){if(counts[k]!==undefined)counts[k]++;return counts},
        show:function(){return counts}
    }
})()

function handleBooking(e){
    e.preventDefault()

    try{
        if(!bookingForm.checkValidity()){
            throw new Error("Validation error - fill required fields.")
        }

        let name=bookingForm.elements['fullName'].value
        let email=bookingForm.elements['emailAddr'].value
        let kind=bookingForm.elements['eventKind'].value
        let date=bookingForm.elements['prefDate'].value

        let remaining=50-counter.show()[kind]

        totalSignups++
        remaining--
        counter.add(kind)

        let btn=document.getElementById('submitBtn')
        btn.innerHTML='<span class="spinner-border spinner-border-sm"></span> Sending...'
        btn.disabled=true

        let payload={name:name,email:email,event:kind,date:date}

        setTimeout(function(){
            fetch('https://jsonplaceholder.typicode.com/posts',{
                method:'POST',
                body:JSON.stringify(payload),
                headers:{'Content-type':'application/json; charset=UTF-8'}
            })
            .then(function(r){return r.json()})
            .then(function(j){
                console.log("Response:",j)

                document.getElementById('confirmBox').classList.add('show')
                document.getElementById('confirmMsg').innerHTML=
                    '<strong>Ref:</strong> #'+Math.floor(Math.random()*9999)+'<br>'
                    +'<strong>Name:</strong> '+name+'<br>'
                    +'<strong>Event:</strong> '+kind.toUpperCase()+'<br>'
                    +'<em>Spots left: '+remaining+'</em><br>'
                    +'Confirmation sent to '+email+'.'

                bookingForm.reset()
                document.getElementById('feeDisplay').innerText='$0.00'
                document.getElementById('charCount').innerText='0'

                sessionStorage.removeItem('draftData')
                formDirty=false
            })
            .catch(function(err){
                console.error("Booking error:",err)
                alert("Booking failed. Please try again.")
            })
            .finally(function(){
                btn.innerHTML='Confirm Booking'
                btn.disabled=false
            })
        },1500)
    }catch(err){
        e.stopPropagation()
        bookingForm.classList.add('was-validated')
        console.warn(err.message)
    }
}

function calcFee(){
    let sel=document.getElementById('eventKind')
    let opt=sel.options[sel.selectedIndex]
    let el=document.getElementById('feeDisplay')

    if(opt&&opt.dataset.fee!==undefined){
        let f=parseFloat(opt.dataset.fee)
        el.innerText=f>0?'$'+f.toFixed(2):'Free'
    }
}

function checkPhone(inp){
    let re=/^[0-9]{3}-[0-9]{3}-[0-9]{4}$/
    let err=document.getElementById('phoneErr')
    if(inp.value&&!re.test(inp.value)){
        inp.setCustomValidity("bad")
        err.innerText="Use 123-456-7890"
    }else{
        inp.setCustomValidity("")
        err.innerText="Enter valid phone"
    }
}

document.addEventListener('DOMContentLoaded',function(){
    let ta=document.getElementById('msgField')
    if(ta){
        ta.addEventListener('keyup',function(){
            document.getElementById('charCount').innerText=this.value.length
        })
    }
})

var formDirty=false

document.querySelectorAll('#bookingForm input,#bookingForm select,#bookingForm textarea').forEach(function(el){
    el.addEventListener('change',function(){
        formDirty=true
        let d={name:document.getElementById('fullName').value,email:document.getElementById('emailAddr').value}
        sessionStorage.setItem('draftData',JSON.stringify(d))
    })
})

window.addEventListener('beforeunload',function(e){
    if(formDirty){
        var msg="You have unsaved changes."
        e.returnValue=msg
        return msg
    }
})

document.getElementById('resetPrefs')&&document.getElementById('resetPrefs').addEventListener('click',function(){
    localStorage.clear()
    sessionStorage.clear()
    document.getElementById('catFilter').value='all'
    applyFilter()
    alert("All preferences reset!")
})

function restoreState(){
    var saved=localStorage.getItem('savedCategory')
    if(saved){
        document.getElementById('catFilter').value=saved
        setTimeout(function(){applyFilter()},100)
    }

    var draft=sessionStorage.getItem('draftData')
    if(draft){
        try{
            var data=JSON.parse(draft)
            if(data.name)document.getElementById('fullName').value=data.name
            if(data.email)document.getElementById('emailAddr').value=data.email
        }catch(e){}
    }
}

document.getElementById('locateBtn')&&document.getElementById('locateBtn').addEventListener('click',function(){
    var out=document.getElementById('geoOut')
    out.innerHTML="Searching..."

    if(navigator.geolocation){
        navigator.geolocation.getCurrentPosition(
            function(pos){
                var la=pos.coords.latitude
                var lo=pos.coords.longitude
                out.innerHTML='<span class="text-success fw-bold">Found!</span><br>Lat: '+la.toFixed(4)+', Lon: '+lo.toFixed(4)+'<br>Showing events within 10mi.'
                console.log("Position:",la,lo)
            },
            function(err){
                switch(err.code){
                    case err.PERMISSION_DENIED:out.innerHTML="Location denied.";break
                    case err.POSITION_UNAVAILABLE:out.innerHTML="Location unavailable.";break
                    case err.TIMEOUT:out.innerHTML="Request timed out.";break
                    default:out.innerHTML="Unknown error."
                }
            },
            {enableHighAccuracy:true,timeout:5000,maximumAge:0}
        )
    }else{
        out.innerHTML="Geolocation not supported."
    }
})

function wireUp(){
    document.getElementById('submitBtn')&&document.getElementById('submitBtn').addEventListener('click',handleBooking)

    var search=document.getElementById('globalSearch')
    if(search){
        search.addEventListener('keydown',function(){
            var self=this
            setTimeout(function(){
                var term=self.value.toLowerCase()
                var filtered=allEvents.filter(function(ev){return ev.name.toLowerCase().includes(term)})
                renderEvents(filtered)
            },50)
        })
    }

    var listBtn=document.getElementById('viewList')
    var gridBtn=document.getElementById('viewGrid')

    if(listBtn&&gridBtn){
        listBtn.addEventListener('click',function(){
            this.classList.add('active')
            gridBtn.classList.remove('active')
            listContainer.classList.add('list-mode')
        })

        gridBtn.addEventListener('click',function(){
            this.classList.add('active')
            listBtn.classList.remove('active')
            listContainer.classList.remove('list-mode')
        })
    }
}

function mediaIsReady(){
    var el=document.getElementById('vidStatus')
    el.classList.remove('hidden')
    setTimeout(function(){
        el.classList.add('fade')
        setTimeout(function(){el.classList.add('hidden')},1000)
    },3000)
}

function zoomGallery(img){
    var zoom=document.getElementById('zoomImg')
    zoom.src=img.src
    document.getElementById('imageModal').classList.add('show')
}

function pushEvent(obj){
    allEvents.push(obj)
    renderEvents(allEvents)
}
