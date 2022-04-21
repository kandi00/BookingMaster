using BookingMaster.Data.Response;

namespace BookingMaster.Services
{
    public interface IAccommodationService
    {
        public Task<AccommodationsResponse> GetAccomodations();
        public Task<AccommodationsResponse> GetAccomodationsByLocation(string? Location);
        public Task<AccommodationsResponse> GetAccomodationsByName(string? Name);
    }
}
