using ApiHeroku.Data.ViewModel;
using ApiHeroku.Services;
using Microsoft.AspNetCore.Mvc;

namespace ApiHeroku.Controllers
{
    [Route("api/[controller]")]
    [ApiController]
    public class BookingController : ControllerBase
    {

        private readonly IBookingService _bookingService;

        public BookingController(IBookingService bookingService)
        {
            _bookingService = bookingService;
        }

        [HttpPost(Name = "add-new-booking")]
        public void AddNewBooking(BookingViewModel newBooking)
        {
            _bookingService.AddNewBooking(newBooking);
        }
    }
}
