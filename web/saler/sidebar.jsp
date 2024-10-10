<%-- 
    Document   : sidebar
    Created on : Sep 18, 2024, 11:58:01 AM
    Author     : FPT SHOP
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<nav id="sidebar" class="sidebar js-sidebar">
    <div class="sidebar-content js-simplebar">
        <a class="sidebar-brand" href="index.html">
            <span class="sidebar-brand-text align-middle">
                Professional Expert
                <sup><small class="badge bg-primary text-uppercase">Pro</small></sup>
            </span>
            <svg class="sidebar-brand-icon align-middle" width="32px" height="32px" viewBox="0 0 24 24" fill="none" stroke="#FFFFFF" stroke-width="1.5"
                 stroke-linecap="square" stroke-linejoin="miter" color="#FFFFFF" style="margin-left: -3px">
            <path d="M12 4L20 8.00004L12 12L4 8.00004L12 4Z"></path>
            <path d="M20 12L12 16L4 12"></path>
            <path d="M20 16L12 20L4 16"></path>
            </svg>
        </a>

        <ul class="sidebar-nav">
            <li class="sidebar-item">
                <a class="sidebar-link" href="pages-profile.html">
                    <i class="align-middle" data-feather="sliders"></i> <span class="align-middle">DashBoard</span>
                </a>
            </li>
            <li class="sidebar-item " >
                <a class="sidebar-link" href="#" onclick="showSortOption()">
                    <i class="fa-solid fa-sort"></i> <span class="align-middle">    Sort</span>
                </a>
                <div id="sortOption" style="display: none">
                    <p class="sidebar-header">Type</p>
                    <input  type="radio" name="type" value="descending" style="margin-left: 20px"> Descending <br>
                    <input type="radio" name="type" value="ascending" style="margin-left: 20px">  Ascending
                    <p class="sidebar-header">Field</p>
                    <input  type="checkbox" name="type" value="cost" style="margin-left: 20px"> Cost <br>
                    <input type="checkbox" name="type" value="date" style="margin-left: 20px">  Registration Date
                </div>
            </li>

            <li class="sidebar-item">
                <a class="sidebar-link" href="pages-invoice.html">
                   <i class="fa-solid fa-filter"></i><span class="align-middle">Filter</span>
                </a>
            </li>
            
      </ul>

    </div>
</nav>
<script>
    // Function to add 'active' class to the currently clicked sidebar item
    window.onload = function () {
        // Get the current URL path
        var currentPath = window.location.pathname;

        // Select all sidebar links (the <a> tags within sidebar-item <li>)
        var sidebarLinks = document.querySelectorAll('.sidebar-item a');

        // Loop through each link
        sidebarLinks.forEach(function (link) {
            // Get the href attribute of each link
            var linkPath = link.getAttribute('href');

            // Compare the current path with the link's href value
            if (currentPath.includes(linkPath)) {
                // Add 'active' class to the parent <li> element
                link.parentElement.classList.add('active');
            }
        });
    };
    
    function showSortOption() {
        var sort_option = document.getElementById('sortOption');
        if(sort_option.style.display==='none'){
              sort_option.style.display='';
        }else{
            sort_option.style.display='none'
        }
    
    
}
</script>