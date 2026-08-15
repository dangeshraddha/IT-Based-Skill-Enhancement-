document.querySelectorAll('a[href^="#"]').forEach(a=>{
  a.addEventListener("click",e=>{
    const el=document.querySelector(a.getAttribute("href"));
    if(el){e.preventDefault();el.scrollIntoView({behavior:"smooth"});}
  });
});
// FAQ is handled by native <details>. Keep the floating buttons accessible.
document.querySelectorAll('a[href^="#"]').forEach(link => {
  link.addEventListener('click', function () {
    const target = document.querySelector(this.getAttribute('href'));
    if (target) target.scrollIntoView({behavior:'smooth'});
  });
});
