using BookingMaster.Data.Response;

namespace BookingMaster.Services
{
    public interface IAccommodationService
    {
        public Task<AccommodationsResponse> GetAccomodations();
    }
}
